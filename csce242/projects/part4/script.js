//THOMAS PETERSON

//CHART BUTTONS
const img = document.getElementById("chart-img");

//Normal Button
document.getElementById("normal").onclick = () => {
    document.getElementById("normal").classList = "isClicked";
    document.getElementById("narcoleptic").classList = "notClicked";
    document.getElementById("both").classList = "notClicked";
    img.src = "../../images/ProjectPart_03_Normal-Sleep.png";
};

//Narcoleptic Button
document.getElementById("narcoleptic").onclick = () => {
    document.getElementById("normal").classList = "notClicked"
    document.getElementById("narcoleptic").classList = "isClicked"
    document.getElementById("both").classList = "notClicked"
    img.src = "../../images/ProjectPart_03_Narc-Sleep.png";
};

//Both Button
document.getElementById("both").onclick = () => {
    document.getElementById("normal").classList = "notClicked";
    document.getElementById("narcoleptic").classList = "notClicked";
    document.getElementById("both").classList = "isClicked";
    img.src = "../../images/ProjectPart_03_Both.png";
};

//User Information Text Boxes
const fn = document.getElementById("First-name");
const ln = document.getElementById("Last-name");
const eml = document.getElementById("Email");
const pnumb = document.getElementById("Phone-number");