const submitButton = document.getElementById('submit-button');
submitButton.addEventListener('click', () => {
    createCampaign();
})

const nameInput = document.getElementById('input-campaign-name');
const bidAmountInput = document.getElementById('input-bid-amount');
const fundInput = document.getElementById('input-campaign-fund');
const cityNameInput = document.getElementById('input-city-name');
const cityRadiusInput = document.getElementById('input-city-radius');
const statusCheckbox = document.getElementById('checkbox-status');

const errorMessage = document.getElementById('error-message');

import { getKeywords } from "./keywordManager.js";

async function createCampaign() {
    if(!verifyInputs()){
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

    try{
        const response = await fetch('/api/campaign', {
            method: 'POST',
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
}

function verifyInputs(){
    if(nameInput.value.trim().length <= 3){
        errorMessage.innerHTML = "The campaign name is too short";
        return false;
    }
    if(bidAmountInput.value < 0.02){
        errorMessage.innerHTML = "The campaign bid amount is too small";
        return false;
    }
    if(fundInput.value < bidAmountInput.value){
        errorMessage.innerHTML = "The campaign fund cannot be less than the bid amount";
        return false;
    }
    if(cityRadiusInput.value < 0){
        errorMessage.innerHTML = "The radius cannot be less than 0";
        return false;
    }
    if(cityNameInput.value.trim().length == 0){
        errorMessage.innerHTML = "City name cannot be empty";
        return false;
    }

    return true;
}
