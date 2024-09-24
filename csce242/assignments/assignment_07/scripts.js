//Random Position Generator
function getRandomPosition(min, max) {
    return Math.random() * (max - min) + min;
};

// Button
const btn = document.getElementById("star-button");
btn.onclick = () => {
    const numOfStars = document.getElementById("star-input").value;
    const starArea = document.getElementById("star-printout");
    
    //Reset Values
    document.getElementById("error").innerHTML = "";
    starArea.innerHTML = "";

    //Error Message Reset/Check
    if(isNaN(numOfStars) || numOfStars <= 0) {
        document.getElementById("error").innerHTML = "ERROR! Invalid Input";
        return;
    }

    //Get section width & height
    const sectW = starArea.offsetWidth;
    const sectH = starArea.offsetHeight;

    //Print stars and assign each a #
    //The idea is that it takes the number inputed, creates a div, adds a star and number to that div, and then continues until the number is reached.
    for (let i = 0; i < numOfStars; i++) {
        const star = document.createElement("div");
        star.classList.add("star");
        star.innerHTML = "★"; // From UTF-8
        
        const ranX = getRandomPosition(0, sectW - 15);
        const ranY = getRandomPosition(0, sectH - 15);

        star.style.left = `${ranX}px`;
        star.style.top = `${ranY}px`;
        starArea.append(star);

        star.onclick = () => {
            document.getElementById("star-number").innerHTML = `This is star #${i+1}`;
        }
    }
};
