const submitButton = document.getElementById('submit-button');
submitButton.addEventListener('click', () => {
    editCampaign();
})

const nameInput = document.getElementById('input-campaign-name');
const bidAmountInput = document.getElementById('input-bid-amount');
const fundInput = document.getElementById('input-campaign-fund');
const cityNameInput = document.getElementById('input-city-name');
const cityRadiusInput = document.getElementById('input-city-radius');
const statusCheckbox = document.getElementById('checkbox-status');

const errorMessage = document.getElementById('error-message');

import { getKeywords, removeKeywords, loadSelectedKeywords } from "./keywordManager.js";
var campaignId = 0;

loadCampaign();

async function loadCampaign() {
    const pathname = window.location.pathname;
    const segments = pathname.split('/');
    campaignId = segments.pop() || segments.pop();

    try {
        const response = await fetch('/api/campaign/' + campaignId, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });

        const campaignJson = await response.json();
        nameInput.value = campaignJson.name;
        bidAmountInput.value = campaignJson.bidAmount;
        cityNameInput.value = campaignJson.city.name;
        cityRadiusInput.value = campaignJson.radius;
        statusCheckbox.checked = campaignJson.status;
        loadSelectedKeywords(campaignJson.keywords);

        document.getElementById('current-funds-value').innerHTML = campaignJson.campaignFund;
    } catch (error) {
        errorMessage.innerHTML = error;
    }

}

async function editCampaign() {
    if (!verifyInputs()) {
        return;
    }
    errorMessage.innerHTML = null;

    var campaignDto = {
        name: nameInput.value,
        bidAmount: bidAmountInput.value,
        campaignFund: fundInput.value,
        status: statusCheckbox.checked,

        keywords: getKeywords(),

        city: cityNameInput.value,
        radius: cityRadiusInput.value,
    };

    try {
        const response = await fetch('/api/campaign/' + campaignId, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(campaignDto)
        });

        if (!response.ok) {
            const errorResponse = await response.text();
            errorMessage.innerHTML = errorResponse;
        } else {
            const responseJson = await response.text();
            errorMessage.innerHTML = responseJson;
        }
    } catch (error) {
        errorMessage.innerHTML = error;
    }

    clearForm();
}

function verifyInputs() {
    if (nameInput.value.trim().length <= 3) {
        errorMessage.innerHTML = "The campaign name is too short";
        return false;
    }
    var bidAmount = parseFloat(bidAmountInput.value);

    if (bidAmount < 0.02) {
        errorMessage.innerHTML = "The campaign bid amount is too small";
        return false;
    }
    if (parseFloat(cityRadiusInput.value) < 0) {
        errorMessage.innerHTML = "The radius cannot be less than 0";
        return false;
    }
    if (cityNameInput.value.trim().length == 0) {
        errorMessage.innerHTML = "City name cannot be empty";
        return false;
    }

    return true;
}

function clearForm() {
    nameInput.value = null;
    bidAmountInput.value = null;
    fundInput.value = null;
    cityNameInput.value = null;
    cityRadiusInput.value = "0";

    removeKeywords();
}