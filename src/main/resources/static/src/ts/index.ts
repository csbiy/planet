// import module
import axios from "axios";
import { config } from "webpack";
import { EmailAuthResponse , Status  } from "./types";

// dom 
const emailSendBtn      = document.querySelector("#email-send-btn") as HTMLButtonElement;
const authValBtn        = document.querySelector("#auth-validate-btn") as HTMLButtonElement; 
const emailInput        = document.querySelector("#email") as HTMLInputElement;
const authInput         = document.querySelector("#auth") as HTMLInputElement;


function getCookie(name :string) :string {
    const val = `; ${document.cookie}`;
    const parts = val.split(`; ${name}=`);
    if(parts.length === 2){
        return parts.pop().split(";").shift();
    }
}

emailSendBtn.addEventListener("click",()=>{
    axios.get("/api/register/email-send?email=" + emailInput.value)
        .then((resp)=>{
            let emailAuthResponse :EmailAuthResponse = resp.data.data;
            if(emailAuthResponse.status.valueOf() === Status.DUPLICATE_MEMBER.valueOf()){
                window.alert(emailAuthResponse.description);
            }
        })
        .catch((err)=>{
            throw err;
        })
})

authValBtn.addEventListener("click",()=>{
    axios.post("/api/register/email-validate",{
        auth : authInput.value

    },{
        headers :{
            [document.querySelector("meta[name='_csrf_header']").getAttribute("content")] : document.querySelector("meta[name='_csrf']").getAttribute("content"),
        }
    })
        .then((resp)=>{
            let emailAuthResponse :EmailAuthResponse = resp.data.data;
            window.alert(emailAuthResponse.description);
            if(emailAuthResponse.status === Status.SUCCESS){
                location.href = "/register";
            }

        })
        .catch((err)=>{
            throw err;
        })
})