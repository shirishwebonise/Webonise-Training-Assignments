
function putInStorage(key, value){
	this.setItem(key, value);
}

function getFromStorage(key){
	return this.getItem(key);
}

function removeFromStorage(key){
	this.removeItem(key);
}

function removeAllFromStorage(){
	this.clear();
}


// use these functions like...
putInStorage.call(localStorage, "entry1", "some random value for entry1");
putInStorage.call(sessionStorage, "someentry", "some random value for entry");

getFromStorage.call(localStorage, "entry1");
getFromStorage.call(sessionStorage, "someentry);

removeFromStorage.call(localStorage, "entry1")
removeFromStorage.call(sessionStorage, "someentry")

removeAllFromStorage.call(localStorage)
removeAllFromStorage.call(sessionStorage)