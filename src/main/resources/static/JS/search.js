function find() {
    if($("#searchInput").val() != ""){
        let url = 'search/find+' + $("#searchInput").val();
        console.log(url);
        window.location.href = url;
    }
}
