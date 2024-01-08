console.log("comment js in~!");

document.getElementById('cmtPostBtn').addEventListener('click',()=>{
    const cmtText = document.getElementById('cmtText');
    if(cmtText.value == null || cmtText.value == ''){
        //값이 존재하지 않는다면...
        alert("댓글을 입력해주세요-!");
        cmtText.focus();
        return false;
    } else {
        //값이 존재한다면...
        let cmtData = {
            bno: bnoVal,
            writer: document.getElementById('cmtWriter').innerText,
            content: cmtText.value
        };
        console.log(cmtData);
        postCommentToServer(cmtData).then(result=>{
            if(result === '1'){
                alert("댓글이 등록되었습니다-!");
                cmtText.value = '';
            }
            //화면에 뿌려봅시당
            spreadCommentList(cmtData.bno);
        });
    }
})

async function postCommentToServer(cmtData){
    try {
        const url = "/comment/post";
        const config = {
            method: "post",
            headers: {
                'content-type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(cmtData)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function getCommentListFromServer(bno, page){
    try {
        const resp = await fetch("/comment/"+bno+"/"+page);
        const result = await resp.json(); //댓글리스트를 리턴해야하므로 제이슨이 와야함!
        return result;
    } catch (error) {
        console.log(error);
    }
}

function spreadCommentList(bno, page=1){
    getCommentListFromServer(bno, page).then(result=>{
        console.log(result);
        //댓글 모양대로 뿌리기
        const div = document.getElementById('accordionExample');
        if(result.cmtList.length > 0){
            div.innerHTML = "";
            for(let i =0; i<result.cmtList.length; i++){
                let li = `<div class="accordion" id="accordionExample">`;
                li += `<div class="accordion-item">`;
                li += `<h2 class="accordion-header">`;
                li += `<button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">`;
                li += `${result.cmtList[i].cno}, ${result.cmtList[i].writer}, ${result.cmtList[i].modAt} </button>`;
                li += `</h2> <div id="collapseOne" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">`;
                li += `<input type="text" class="form-control cmtText" value=${result.cmtList[i].content}>`;
                li += `</div></div></div>`;
                div.innerHTML += li;
            }
        } else {
            div.innerHTML = `<li class="list-group-item">Comment List Empty</li>`;
        }
    });
}