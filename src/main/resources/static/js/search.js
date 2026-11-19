$(document).ready(function(){
    $('#searchBtn').on('click', function(e){
        search(e);
    });
    //엔터키
    $('input[name=keyword]').on('keypress', function(e){
        if(e.key == 'Enter'){
//            e.preventDefault(); //엔터키로 인한 기본 폼 제출 방지 (새로고침 방지)
            search(e);
        }
    })
});


function search(event) {
    const searchForm = $('#search_form').serializeArray();

    let keyword = $('input[name=keyword]').val().trim().replace(/\s+\g,''); //공백제거(\s : 공백문자, g : 전역
    console.log("event =>"+event);
    $.ajax({
        type:'GET',
        data:{ keyword : keyword},
        url:'/api/search',
        success:function(data){
        console.log(data);
            $('#resPc').text(data.pcCnt);
            $('#resMobile').text(data.mobileCnt);
            $('#resTotal').text(data.totalCnt);
        },
        error: function(xhr, status, error){ //error 콜백은 무조건 인자가3개
            console.error("통신 실패:",error);
        }

    });
}