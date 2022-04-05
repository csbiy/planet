

function addCsrfAttribute(header :any) {
    console.log(document.querySelector("meta[name='_csrf_header']").getAttribute("content"));
    console.log(document.querySelector("meta[name='_csrf']").getAttribute("content"))
    header[document.querySelector("meta[name='_csrf_header']").getAttribute("content")] = document.querySelector("meta[name='_csrf']").getAttribute("content");
    console.log(header)
    return header;
}

export {addCsrfAttribute}