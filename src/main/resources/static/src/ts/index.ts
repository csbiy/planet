// import module
import axios from "axios";
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
    axios.get("/register/email-send?email=" + emailInput.value)
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
    axios.post("/register/email-validate" ,{
        auth : authInput.value 
    })
    .then((resp)=>{
        let emailAuthResponse :EmailAuthResponse = resp.data.data;
        window.alert(emailAuthResponse.description);
        console.log(emailAuthResponse);
        console.log(emailAuthResponse.status.valueOf());
        console.log(Status.SUCCESS.valueOf());
        //TODO  :  emailAuthResponse.status.valueOf() != Status.SUCCESS.valueOf()
        if(emailAuthResponse.status.valueOf() == Status.SUCCESS.valueOf()){
            location.href = "/register";
        }      

    })
    .catch((err)=>{
        throw err;
    })
})