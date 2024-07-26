package com.task.campaign.database;

import com.task.campaign.database.model.City;
import com.task.campaign.database.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class DefaultDataLoader implements CommandLineRunner {
    final CityRepository cityRepository;

    @Override
    public void run(String... args) throws Exception {
        loadCities();
    }

    private void loadCities(){
        if(cityRepository.count() > 0){
            return;
        }

        String[] defaultCities = {"Warszawa", "Kraków", "Gdańsk", "Poznań", "Katowice", "Szczecin", "Lublin", "Wrocław", "Białystok", "Łódź", "Bydgoszcz", "Gdynia", "Częstochowa", "Radom", "Sosnowiec", "Toruń", "Kielce", "Rzeszów", "Gliwice", "Zabrze", "Olsztyn", "Bielsko-Biała", "Bytom", "Zielona Góra", "Rybnik", "Ruda Śląska", "Opole", "Tychy", "Gorzów Wielkopolski", "Dąbrowa Górnicza", "Płock", "Elbląg", "Wałbrzych", "Włocławek", "Tarnów", "Chorzów", "Koszalin", "Kalisz", "Legnica", "Grudziądz", "Słupsk", "Jaworzno", "Jastrzębie-Zdrój", "Nowy Sącz", "Jelenia Góra", "Konin", "Piotrków Trybunalski", "Lubin", "Inowrocław", "Suwałki", "Mysłowice", "Ostrowiec Świętokrzyski", "Siemianowice Śląskie", "Gniezno", "Głogów", "Zamość", "Chełm", "Leszno", "Tomaszów Mazowiecki", "Przemyśl", "Stalowa Wola", "Kędzierzyn-Koźle", "Łomża", "Żory", "Tarnowskie Góry", "Pabianice", "Świdnica", "Biała Podlaska", "Ełk", "Pruszków", "Ostrołęka", "Stargard", "Legionowo", "Tarnobrzeg", "Puławy", "Racibórz", "Wejherowo", "Radomsko", "Skierniewice", "Starachowice", "Kutno", "Siedlce", "Nysa", "Mielec", "Piła", "Ostrów Wielkopolski", "Lubartów", "Jarosław", "Malbork", "Kraśnik", "Nowa Sól", "Zgierz", "Kołobrzeg", "Będzin", "Otwock", "Swarzędz", "Knurów", "Bochnia", "Świętochłowice", "Lębork"};
        for(String cityName:defaultCities){
            cityRepository.save(new City(cityName));
        }
    }
}
