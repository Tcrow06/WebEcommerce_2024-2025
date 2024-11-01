function getCookie(name){
    let matches = document.cookie.match(new RegExp(
        "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
    ));
    return matches ? decodeURIComponent(matches[1]) : undefined;
}

function verifyToken(){
    const token = getCookie('token');
    if(!token){
        console.error("Token không tồn tại");
        return;
    }
    fetch('/verifyToken', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + token
        }
    })
        .then(response =>{
            if(!response.ok){
                throw  new Error('Network response(verify) was not ok ' + response.statusText)
            }
            return response.json();
        })
        .then (data => {
            if(data.valid){
                console.log("Token hợp lệ");
                refreshAuthToken(token);
            }else{
                console.error("Token không hợp lệ");

            }
        })
        .catch(error => console.error('Error: ', error));
}
function refreshAuthToken(oldToken){
    fetch('/refreshAuthToken',{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + oldToken
        }

    })
        .then(response => {
            if(!response.ok){
                throw new Error('Network response(refresh) was not ok ' + response.statusText)
            }
            return response.json()
        })
        .then (data => {
            console.log("Response from /refreshAuthToken:", data);
            if(data.valid){
                document.cookie = "token=" + data.token + ";path =/";
                console.log('Token đã được làm mới');

            }else{
                console.error('Lỗi làm mới token');
            }
        })
        .catch(error => console.error("Error: " + error));

}
window.onload = function() {
    verifyToken();
}