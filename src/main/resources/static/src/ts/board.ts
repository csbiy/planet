const voteNums = document.querySelectorAll(".board .vote-num") as NodeListOf<HTMLTableElement>;
voteNums.forEach((voteNum)=>{
    if( Number(voteNum.innerText) <0){
        voteNum.appendChild(createEmoji("far fa-frown"));
    }else{
        voteNum.appendChild(createEmoji("far fa-smile"));
    }
})

function createEmoji( className :string) :HTMLElement{
    let  i = document.createElement("i");
    i.setAttribute('class',className);
    return i;
}