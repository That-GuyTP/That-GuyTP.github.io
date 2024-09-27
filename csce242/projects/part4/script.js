//THOMAS PETERSON

//STAR PRINTING
document.getElementById("star-printout").onload = () => {
    const starArea = document.getElementById("star-printout");
    
    for( let i = 0; i < maxStars; i++) {
        const star = document.createElement("div");
        star.classList.add("star");
        star.innerHTML = "★";

        const ranX = getRandomPosition(0, sectW - 15);
        const ranY = getRandomPosition(0, sectH - 15);

        star.style.left = `${ranX}px`;
        star.style.top = `${ranY}px`;
        starArea.append(star);
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