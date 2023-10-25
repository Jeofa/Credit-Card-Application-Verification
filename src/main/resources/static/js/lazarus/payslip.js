
var myHeaders = new Headers();
myHeaders.append("orgId", '9880b7682d9843cb94');
myHeaders.append("authKey", '59e3a937f44240a18896');

var formdata = new FormData();
formdata.append("file", 'front.jpeg', 'id');

var requestOptions = {
  method: 'POST',
  headers: myHeaders,
  body: formdata,
  redirect: 'follow'
};

fetch("https://api.lazarusforms.com/api/forms/generic/zip", requestOptions)
  .then(response => response.text())
  .then(result => console.log(result))
  .catch(error => console.log('error', error));