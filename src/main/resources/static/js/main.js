window.onload = () => {
	const header = document.getElementById("header");
	const container = document.getElementById("container");
	const footer = document.getElementById("footer");
	
	container.style.minHeight = document.body.clientHeight - footer.clientHeight - header.clientHeight;
}