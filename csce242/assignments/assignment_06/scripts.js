//The reason I declare a constant before the function is because I often run into an error where the button doesn't work if not made into a constant. This is very inconsistent and I typically declare the element as a const to help with this.

/* Arrow Navs */
const uparrow = document.getElementById("toggle-arrow-up");
uparrow.onclick = () => {
    document.getElementById("ex-nav").classList.toggle("hidden-small");
    document.getElementById("toggle-arrow-down").classList.toggle("hidden-small");
    document.getElementById("toggle-arrow-up").classList.toggle("hidden-small");
};

const downarrow = document.getElementById("toggle-arrow-down");
downarrow.onclick = () => {
    document.getElementById("ex-nav").classList.toggle("hidden-small");
    document.getElementById("toggle-arrow-down").classList.toggle("hidden-small");
    document.getElementById("toggle-arrow-up").classList.toggle("hidden-small");
};

/* Exercise Toggles */
document.getElementById("ex1").onclick = (e) => {
    e.preventDefault();//Prevents the default action of whatever it's connected to. In this case the link will not go to a destination.
    document.getElementById("ex-slider").classList.toggle("hidden");
    document.getElementById("ex-resizer").classList.add("hidden");
};

document.getElementById("ex2").onclick = (e) => {
    e.preventDefault();//Prevents the default action of whatever it's connected to. In this case the link will not go to a destination.
    document.getElementById("ex-resizer").classList.toggle("hidden");
    document.getElementById("ex-slider").classList.add("hidden");
};

/* Recolor Slider */
const slider = document.getElementById("slider");
const background = document.getElementById("ex-slider");
const text = document.getElementById("text");
slider.onchange = () => {
    const rgbColor = 'rgb(' + slider.value + ', 0, 0)';
    background.style.setProperty("background-color", rgbColor);

    if(slider.value < 100) {
        text.innerHTML = "Dark Red";
    }else if (slider.value >= 100 && slider.value < 200) {
        text.innerHTML = "Red";
    }else {
        text.innerHTML = "Bright Red";
    }
};

/* Image Resizer */
document.querySelectorAll(".size-button").forEach(button => {
    button.onclick = (e) => {
        const size = e.target.getAttribute("id");
        const img = document.getElementById("random-img");
        if (size == "small") {
            img.src = "https://picsum.photos/200";
        }else if (size == "medium") {
            img.src = "https://picsum.photos/400";
        }else if (size == "large") {
            img.src = "https://picsum.photos/600";
        }else {
            img.src = "https://picsum.photos/100";
        }
    }
});