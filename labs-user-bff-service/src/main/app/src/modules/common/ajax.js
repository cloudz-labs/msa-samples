export function ajax(url, callback, data, x) {
	var ActiveXObject;
	try {
		x = new(window.XMLHttpRequest || ActiveXObject)('MSXML2.XMLHTTP.3.0');
		x.open(data ? 'POST' : 'GET', url, 1);
		x.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
		x.setRequestHeader('Content-type', 'application/json');
		x.onreadystatechange = function () {
			x.readyState > 3 && callback && callback(x.responseText, x);
		};
		x.send(data ? JSON.stringify(data) : data);
	} catch (e) {
		window.console && console.log(e);
	}
};

function status(response) {
	if(response.ok) {
		return Promise.resolve(response);
	} else {
		return response.json().then(err=>{throw err;});
	}
}

function getQueryString(paramObj) {
	if(!paramObj || !Object.keys(paramObj).length) return "";
	return Object.keys(paramObj).map(key=>(
			encodeURIComponent(key) + '=' + encodeURIComponent(paramObj[key])
		)).join('&');
}
function jsonConverter(res) {
	return res.json().catch(err=>Promise.resolve({}));
}

const httpModule = {
	get : function(url, paramObj){
		return new Promise((resolve, reject)=>{
			let query = "";
			if(paramObj && Object.keys(paramObj).length) {
				query = "?" + getQueryString(paramObj);
			}
			fetch(url + query)
			.then(status)
			.then(jsonConverter)
			.then(data=>{
				resolve(data);
			})
			.catch(err=>{
				reject(err);
			});
		});
	},
	post : function(url, bodyObj){
		if(bodyObj instanceof HTMLFormElement) {
			return this.form.apply(this, arguments);
		}
		return new Promise((resolve, reject) => {
			fetch(url, {
				method:'POST',
				headers : {
					'Content-Type' : 'application/json;charset=UTF-8'
				},
				body : JSON.stringify(bodyObj || {})
			})
			.then(status)
			.then(jsonConverter)
			.then(data=>{
				resolve(data);
			})
			.catch(err=>{reject(err);});
		});
	},
	submit : function(url, formData){
		if(formData instanceof HTMLFormElement) {
			return this.form.apply(this, arguments);
		}
		return new Promise((resolve, reject) => {
			fetch(url, {
				method: 'POST',
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded;charset=UTF-8'
				},
				body : getQueryString(formData)
			})
			.then(status)
			.then(jsonConverter)
			.then(data=>{
				resolve(data);
			})
			.catch(err=>{reject(err);});
		});
	},
	form : function(url, form) {
		if(!(form instanceof HTMLFormElement)) {
			return Promise.reject({message:"An instance of HTMLFormElement should be provided"});
		}
		return new Promise((resolve, reject) => {
			fetch(url, {
				method : 'POST',
				body : new FormData(form)
			})
			.then(status)
			.then(jsonConverter)
			.then(data=>{
				resolve(data);
			})
			.catch(err=>{reject(err);});
		});
	}
};

const mod = {};
Object.keys(httpModule)
	.forEach(method=>{
		mod[method] = function(){ return httpModule[method].apply(httpModule, arguments);
	}});

export {mod as http};