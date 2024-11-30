    let urlParams = new URLSearchParams(window.location.search);
    let path = window.location.pathname;
    // let sendDirection = path.split('/')[1];
    let sendDirection = path;
    let stateParam = urlParams.get("state");
    if (stateParam) {
        try {
            let stateObj = JSON.parse(decodeURIComponent(stateParam));
            sendDirection = stateObj["send-direction"] || '';
        } catch (error) {
        console.error("Error parsing state parameter:", error);
        }
    }
    let sendDirectionParam = urlParams.get("send-direction")
    let googleLogin = document.getElementById("google-id");
    let facebookLogin = document.getElementById("facebook-id");
    let login= document.getElementById("btn-login")
    if(googleLogin){
        googleLogin.addEventListener('click', function (event){
            event.preventDefault();
            let sateParam = (JSON.stringify({"send-direction": sendDirection, provider:'google' }))
            let newHref = this.href + "&state=" + sateParam;
            window.location.href = newHref;
        })
    }
    if(facebookLogin){
        facebookLogin.addEventListener('click',function (event){
            event.preventDefault();
            let stateParam = (JSON.stringify({"send-direction": sendDirection, provider:'facebook'}))
            let newHref = this.href + "&state=" + stateParam;
            window.location.href = newHref;
        })
    }
