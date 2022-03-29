import axios from "axios";
/*
import Quill from "quill";

 
const submitBtn = document.querySelector("#submitBtn");
const editorContainer = document.querySelector("#editor");
let container :Array<HTMLInputElement> = [];

const editor = new Quill("#editor", {
    modules: { 
        toolbar: [['bold', 'italic'], ['link', 'image']] , 
    },
    theme: 'snow',
});
editor.getModule("toolbar").addHandler("image", imgHandler);

function imgHandler(){
    const input = document.createElement("input");
    input.setAttribute("type","file");
    input.setAttribute("accept","images/!*")
    input.setAttribute("hidden","true");
    input.click();
    input.addEventListener("change",()=>{
        const file = input.files.item(0);
        if(file){
            const img = document.createElement("img");
            const width = window.getComputedStyle(editorContainer).width;
            img.width = Number(width.replace("px","")) * 3/4;        
            img.src = URL.createObjectURL(file);
            const pImg = document.createElement("p");
            container.push(input);
            pImg.appendChild(img);
            document.querySelector("#editor .ql-editor").appendChild(pImg);
        }
    })
}
submitBtn.addEventListener("click",()=>{
    let target = document.querySelector("#editor .ql-editor").children;
    let content = "";
    let formData = new FormData();
    let cnt = 0;
    for(let i = 0 ; i < target.length ; i++){
        let curr = target.item(i);
        if(curr.firstChild.nodeName == "IMG"){
            content+= `<img> ${String(cnt)} <img/>`;
            cnt++;
        }else{
            content+=`<p> ${curr.innerHTML} <p/>`;
        }
    }
    let fileList :Array<File> = container.map((item) => item.files[0]);
    fileList.forEach((file)=>formData.append("file",file,"file"));
    formData.append("content",content);
    axios.post("/board",formData,{
            headers: {'Content-Type': 'multipart/form-data'}
        })
        .then((res)=>console.log(res))
        .catch((err) => {console.log(err); throw err})
})*/
