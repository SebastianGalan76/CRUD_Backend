const searchInput = document.getElementById('input-city-name');

//Upper panel
const listPanel = document.getElementById('city-list-panel');
const listContainer = document.getElementById('city-list-container');

var cityArray = [];

searchInput.addEventListener('focus', function () {
    listPanel.style.display = 'block';
    searchInput.classList.add("focused");
});

searchInput.addEventListener('input', function () {
    const filteredArray = cityArray.filter(city =>
        city.name.toLowerCase().startsWith(searchInput.value.toLowerCase())
    );
    populateList(filteredArray);
});

document.addEventListener('click', function (event) {
    if (!listPanel.contains(event.target) && !searchInput.contains(event.target)) {
        listPanel.style.display = 'none';
        searchInput.blur();
        searchInput.classList.remove("focused");
    }
    else {
        searchInput.focus();
    }
});

loadCities();

async function loadCities() {
    const response = await fetch('/api/city', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
    });
    

    if (!response.ok) {
        const errorResponse = await response.json();
        throw new Error(`Error: ${errorResponse.message}`);
    }
    else {
        cityArray = await response.json();

        populateList(cityArray);
    }
}

function populateList(cityArray) {
    listContainer.innerHTML = null;

    cityArray.forEach(city => {
        const itemDiv = document.createElement('div');
        itemDiv.className = 'item';

        const label = document.createElement('span');
        label.innerHTML = city.name;

        itemDiv.addEventListener('click', () => selectCity(city));

        itemDiv.appendChild(label);
        listContainer.appendChild(itemDiv);
    });
}

function selectCity(city) {
    searchInput.value = city.name;
}