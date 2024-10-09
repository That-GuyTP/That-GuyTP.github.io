const getFlavors = async() => {
    const url = "https://portiaportia.github.io/json/ice-creams.json"
    try {
        const response = await fetch(url);
        return response.json();
    } catch(error) {
        console.log("---Error retreiving URL data.---");
        console.log(error);
    }
};

const showFlavors = async() => {
    const flavors = await getFlavors();
    flavors.forEach((flavor) => {
        document.getElementById("flavor-div").append(getFlavorDiv(flavor));
    });
};

const getFlavorDiv = (flavor) => {
    const div = document.createElement("div");
    div.classList.add("one");

    // Retrieve Image
    const img = document.createElement("img");
    console.log(flavor.image);
    img.src = "./images/" + flavor.image;
    img.classList.add("flavor-img");
    div.append(img);

    //Add Overlay
    const overlay = document.createElement("div");
    overlay.classList.add("overlay");
    overlay.innerText = flavor.name;

    div.append(overlay);

    return div;
};

showFlavors();