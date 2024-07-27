const campaignTable = document.getElementById('campaign-table');
loadCampaigns();

export async function loadCampaigns() {
    const response = await fetch('/api/campaign', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
    });

    try{
        if (!response.ok) {
            const errorResponse = await response.json();
            throw new Error(`Error: ${errorResponse.message}`);
        }
        else {
            var responseJson = await response.json();
            populateList(responseJson);
        }
    } catch(error){
        console.log(error);
    }
}

function populateList(listArray) {
    listArray.forEach(campaignJson => {
        var table = campaignTable.getElementsByTagName('tbody')[0];
        var newRow = table.insertRow();

        var cell1 = newRow.insertCell(0);
        var cell2 = newRow.insertCell(1);
        var cell3 = newRow.insertCell(2);
        var cell4 = newRow.insertCell(3);
        var cell5 = newRow.insertCell(4);
        var cell6 = newRow.insertCell(5);
        var cell7 = newRow.insertCell(6);

        cell1.innerHTML = campaignJson.name;
        cell2.innerHTML = campaignJson.bidAmount;
        cell3.innerHTML = campaignJson.campaignFund;
        cell4.innerHTML = campaignJson.keywords;
        cell5.className = "location";
        cell5.innerHTML = campaignJson.city.name + " (+"+campaignJson.radius+" km)";
        cell6.innerHTML = campaignJson.status;
        cell7.className = "action-buttons";
        cell7.innerHTML = '<i class="fa-solid fa-pen-to-square"></i><i class="fa-solid fa-trash"></i>';

        var editIcon = cell7.querySelector('.fa-pen-to-square');
        var deleteIcon = cell7.querySelector('.fa-trash');

        editIcon.onclick = function() {
            editCampaign(campaignJson);
        };

        deleteIcon.onclick = function() {
            deleteCampaign(campaignJson, newRow);
        };
    });
}

function editCampaign(campaignJson){
    window.location.href = '/edit/'+campaignJson.id;
}

async function deleteCampaign(campaignJson, row){
    try{
        await fetch('/api/campaign/'+campaignJson.id, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
        });

        row.remove();
    } catch (error) {
        console.log(error);
    }
}