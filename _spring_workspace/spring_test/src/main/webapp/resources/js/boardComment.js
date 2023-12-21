console.log("boardComment.js in");
console.log(">>> bno >> {} "+bnoVal);

document.getElementById("cmtAddBtn").addEventListener('click', ()=> {
    const cmtText = document.getElementById("cmtText").value;
    const cmtWriter = document.getElementById("cmtWriter").innerText;
    if(cmtText == "" || cmtText == null){
alert("댓글을 입력해주세요.");
document.getElementById('cmtText').focus();
return false;
    } else {
let cmtData = {
    bno: bnoVal,
    writer: cmtWriter,
    content: cmtText
};
console.log(cmtData);
postCommentToServer(cmtData).then(result=>{
    console.log(result);
    if(result === "1"){
     alert("댓글이 등록되었습니다!");
     //화면에 뿌리기..는 나중에 할거임
     spreadCommentList(bnoVal);
    }
})
    }
})

//js에서 주석은 위험...주석을 주석으로 인식안할수도 있음..
//비동기 통신 구문
//post: 데이터 삽입
//get: 조회 => 생략 가능
//put(patch): 수정 => 보내지 않은 값을 null 처리
//delete: 삭제
//목적지 경로
//Headers는 무조건 객체로 받아야함
async function postCommentToServer(cmtData){
try {
    const url = "/comment/post";
    const config = {
    method:"post",
    headers:{
      "content-type":"application/json; charset=utf-8"
    },
    body: JSON.stringify(cmtData)
    };
    const resp =  await fetch(url, config);
    const result = await resp.text();
    return result;
} catch (error) {
    console.log(error);
}
}

async function getCommentListFromServer(bno){
    try {
        const resp = await fetch("/comment/list/"+bno);
        const result = resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}

//댓글 뿌리기
function spreadCommentList(bno){
    getCommentListFromServer(bno).then(result=>{
        console.log(result);
        const div = document.getElementById('accordionExample');
        if(result.length > 0){
            div.innerHTML = "";
            for(let i=0;i<result.length;i++){
                let add = `<div class="accordion-item">`;
                add += `<div class="accordion-header">`;
                add += `<button class="accordion-button" type="button" data-bs-toggle="collapse" `;
                add += `data-bs-target="#collapse${i}" aria-expanded="true" aria-controls="collapse${i}">`;
                add += `no.${result[i].cno} | ${result[i].writer} | ${result[i].reg_date} </button></h2>`;            
                add += `<div id="collapse${i}" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">`;           
                add += `<div class="accordion-body">`;
                add += `<button type="button" data-cno="${result[i].cno}" class="btn btn-warning btn-sm cmtModBtn">수정</button>`;
                add += `<button type="button" data-cno="${result[i].cno}" class="btn btn-danger btn-sm cmtDelBtn">삭제</button>`;
                add += `<input type="text" class="form-control cmtText" value="${result[i].content}">`;
                add += `</div></div></div>`;

                div.innerHTML += add;
            }
        } else {
            div.innerHTML = `<div class="accordion-body">Comment List Empty</div>`;
        }
    })
}

async function removeCommentFromServer(cnoVal){
try {
    const url = "/comment/"+cnoVal;
    const config = {
        method: "delete"

    }
    const resp = await fetch(url, config);
    const result = await resp.text();
    return result;
} catch (error) {
    console.log(error);
}
}

async function updateCommentToServer(cmtData){
    try {
        const url = "/comment/modify";
        const config = {
            method : "put",
            headers: {
                'content-type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(cmtData)
        }
        const resp = await fetch(url, config);
        const result = resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

document.addEventListener('click',(e)=>{
    console.log(e.target);
    if(e.target.classList.contains('cmtDelBtn')){
        //필요한 cno 변수값을 얻기
        let cnoVal = e.target.dataset.cno; 
        console.log (cnoVal);
        removeCommentFromServer(cnoVal).then(result => {
            if(result === "1"){
                alert("댓글 삭제 성공~!");
                spreadCommentList(bnoVal);
            }
        });
    }
    if(e.target.classList.contains('cmtModBtn')){
       let cnoVal = e.target.dataset.cno;
       let div = e.target.closest('div');
       let cmtText = div.querySelector(".cmtText").value;
       let cmtData = {
        cno: cnoVal,
        content: cmtText
       };
       console.log(cmtData);
       updateCommentToServer(cmtData).then(result=>{
        if(result === "1"){
            alert("댓글 수정 성공~!");
            spreadCommentList(bnoVal);
        }
       })
    }
})

