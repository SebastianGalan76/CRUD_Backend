const searchInput = document.getElementById('input-keyword');
const datalist = document.getElementById('keywords-typeahead-list');
const keywordContainer = document.getElementById('selected-keyword-container');

var keywordArray = [];
var selectedKeywords = [];

searchInput.addEventListener('input', function () {
    if(searchInput.value.length > 0){
        const filteredArray = keywordArray.filter(keyword =>
            keyword.name.toLowerCase().startsWith(searchInput.value.toLowerCase())
        ).slice(0,3);
    
        populateList(filteredArray);
    }
    else{
        datalist.innerHTML = null;
    }
});

searchInput.addEventListener('keydown', function(event) {
    if (event.key === 'Enter') {
        if(searchInput.value.length > 0){
            createKeywordView(searchInput.value);

            searchInput.value = null;
            datalist.innerHTML = null;
        }
    }
});

loadKeywords();

export function getKeywords(){
    return selectedKeywords;
}

async function loadKeywords() {
    const response = await fetch('/api/keyword', {
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
        keywordArray = await response.json();
    }
}

function populateList(array) {
    datalist.innerHTML = null;

    array.forEach(item => {
        const option = document.createElement('option');
        option.value = item.name;
        datalist.appendChild(option);
    });
}

function createKeywordView(keyword) {
    const itemDiv = document.createElement('div');
    itemDiv.className = 'item';
    itemDiv.textContent = keyword;
    
    const removeButton = document.createElement('i');
    removeButton.className = 'remove-button fa-solid fa-trash';
    
    removeButton.addEventListener('click', () => {
        removeKeyword(keyword);
        itemDiv.remove();
    })

    itemDiv.appendChild(removeButton);
    keywordContainer.appendChild(itemDiv);
    selectedKeywords.push(keyword);
}

function removeKeyword(keyword) {
    selectedKeywords = selectedKeywords.filter(item => item !== keyword);
}

function selectCity(city) {
    searchInput.value = city.name;
}