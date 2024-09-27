//Creating the Array
let pictures = [];
pictures["birthday"] = "This is a photo of me on my birthday!!!"; 
pictures["clown"] = "I love clowns";
pictures["rain"] = "Make sure to wear a coat when it rains";
pictures["read"] = "I love reading books";
pictures["shovel"] = "Getting yard work done is fun";
pictures["work"] = "Gotta be studious!";

//Variables
let table = document.getElementById("pic-table");
let index = 0;
let tr;

//Instantiating Array
for (let picture in pictures) {
    if (index % 3 == 0) {
        tr = document.createElement("tr");
        table.append(tr);
    }

    let td = document.createElement("td");
    let img = document.createElement("img");
    img.src = `./images/${picture}.jpg`;
    img.classList.add("table-img");
    td.append(img);
    tr.append(td);
    index++;
    td.onclick = () => {
        let desc = document.getElementById("pic-desc");
        let name = document.getElementById("pic-name");
        desc.innerHTML = `${pictures[picture]}`;
        name.innerHTML = `${picture}`;
    }
};