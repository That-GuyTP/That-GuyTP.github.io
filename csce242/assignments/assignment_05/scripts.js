// THOMAS PETERSON

//VARIABLES
count = 0;

//CLICK COUNTER
const b1 = document.getElementById("counter");
b1.onclick = () => {
    count++;
    document.getElementById("counter").innerHTML = count;
};

//IMAGE REFRESHER
const b2 = document.getElementById("refresher");
b2.onclick = () => {
    location.reload();
};

//SLIDER
const slider = document.getElementById("slider");
const image = document.getElementById("image");
slider.addEventListener("input", (e) => {
    const sliderValue = e.target.value;
    image.style.left = '${sliderValue}px';
});
