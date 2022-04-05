import axios from "axios";
import { addCsrfAttribute } from "./csrf";
import {   NickNameResponse , Status  } from "./types";

const nickNameInput       = document.querySelector("#nickName") as HTMLInputElement;
const nickNameDupCheckBtn = document.querySelector("#dup-check-btn") as HTMLButtonElement;


nickNameDupCheckBtn.addEventListener("click",()=>{
    axios.post("/api/register/nickname",{
        nickName : nickNameInput.value
    },{
        headers : addCsrfAttribute({})
    })
    .then((resp)=>{
        let nickNameResponse :NickNameResponse = resp.data.data;
        window.alert(nickNameResponse.description);
        //TODO: 닉네임 중복여부에 따라 form제출을 막으려고함 
    })
    .catch((err)=>{
        throw err;
    })  
})
