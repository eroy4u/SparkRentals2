
function swapPage(page_num){
    var page_element = document.getElementById('id_page');
    if (page_element){
        page_element.value = page_num;
        var form = document.getElementById('id_search_form');
        form.submit();

    }
    
}