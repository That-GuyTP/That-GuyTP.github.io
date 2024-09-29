//THOMAS PETERSON

//STAR PRINTING
function getRandomPosition(min, max) {
    return Math.random() * (max - min) + min;
};

const pageHeight = document.documentElement.scrollHeight;
const pageWidth = document.documentElement.scrollWidth;

window.onload = () => {
    for( let i = 0; i < 70; i++) {
        const star = document.createElement("div");
        star.className = "star";
        star.innerHTML = "★";

        star.style.top = `${Math.random() * pageHeight}px`;
        star.style.left = `${Math.random() * pageWidth}px`;
        document.body.append(star);
    }
};

//CHART BUTTONS
const img = document.getElementById("chart-img");

//Normal
document.getElementById("normal").onclick = () => {
    document.getElementById("normal").classList = "isClicked";
    document.getElementById("narcoleptic").classList = "notClicked";
    document.getElementById("both").classList = "notClicked";
    img.src = "../../images/ProjectPart_03_Normal-Sleep.png";
};

//Narcoleptic
document.getElementById("narcoleptic").onclick = () => {
    document.getElementById("normal").classList = "notClicked"
    document.getElementById("narcoleptic").classList = "isClicked"
    document.getElementById("both").classList = "notClicked"
    img.src = "../../images/ProjectPart_03_Narc-Sleep.png";
};

//Both
document.getElementById("both").onclick = () => {
    document.getElementById("normal").classList = "notClicked";
    document.getElementById("narcoleptic").classList = "notClicked";
    document.getElementById("both").classList = "isClicked";
    img.src = "../../images/ProjectPart_03_Both.png";
};

//TEXT BOXES
const fn = document.getElementById("First-name");
const ln = document.getElementById("Last-name");
const eml = document.getElementById("Email");
const pnumb = document.getElementById("Phone-number");