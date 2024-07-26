package com.task.campaign.database;

import com.task.campaign.database.model.City;
import com.task.campaign.database.model.Keyword;
import com.task.campaign.database.repository.CityRepository;
import com.task.campaign.database.repository.KeywordRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class DefaultDataLoader implements CommandLineRunner {
    final CityRepository cityRepository;
    final KeywordRepository keywordRepository;

    @Override
    public void run(String... args) throws Exception {
        loadCities();
        loadKeywords();
    }

    private void loadKeywords() {
        if (keywordRepository.count() > 0) {
            return;
        }

        String[] defaultKeywords = {
                "smartfon",
                "nawilżacz powietrza",
                "biurko ergonomiczne",
                "naprawa komputerów",
                "usługi konsultingowe",
                "obsługa klienta",
                "oszczędność czasu",
                "poprawa zdrowia",
                "wzrost sprzedaży",
                "Warszawa",
                "Kraków",
                "Gdańsk",
                "młodzi profesjonaliści",
                "rodziny z dziećmi",
                "sportowcy",
                "gdzie kupić",
                "najlepszy sposób na",
                "jak naprawić",
                "Apple",
                "Just Do It",
                "Najlepsze w swojej klasie",
                "20% zniżki",
                "oferta specjalna",
                "wyprzedaż letnia",
                "kup teraz",
                "zarejestruj się",
                "sprawdź ofertę",
                "optymalizacja SEO",
                "Google Ads",
                "SEM kampania"
        };
        for (String keyword : defaultKeywords) {
            keywordRepository.save(new Keyword(keyword));
        }
    }

    private void loadCities() {
        if (cityRepository.count() > 0) {
            return;
        }

        String[] defaultCities = {"Warszawa", "Kraków", "Gdańsk", "Poznań", "Katowice", "Szczecin", "Lublin", "Wrocław", "Białystok", "Łódź", "Bydgoszcz", "Gdynia", "Częstochowa", "Radom", "Sosnowiec", "Toruń", "Kielce", "Rzeszów", "Gliwice", "Zabrze", "Olsztyn", "Bielsko-Biała", "Bytom", "Zielona Góra", "Rybnik", "Ruda Śląska", "Opole", "Tychy", "Gorzów Wielkopolski", "Dąbrowa Górnicza", "Płock", "Elbląg", "Wałbrzych", "Włocławek", "Tarnów", "Chorzów", "Koszalin", "Kalisz", "Legnica", "Grudziądz", "Słupsk", "Jaworzno", "Jastrzębie-Zdrój", "Nowy Sącz", "Jelenia Góra", "Konin", "Piotrków Trybunalski", "Lubin", "Inowrocław", "Suwałki", "Mysłowice", "Ostrowiec Świętokrzyski", "Siemianowice Śląskie", "Gniezno", "Głogów", "Zamość", "Chełm", "Leszno", "Tomaszów Mazowiecki", "Przemyśl", "Stalowa Wola", "Kędzierzyn-Koźle", "Łomża", "Żory", "Tarnowskie Góry", "Pabianice", "Świdnica", "Biała Podlaska", "Ełk", "Pruszków", "Ostrołęka", "Stargard", "Legionowo", "Tarnobrzeg", "Puławy", "Racibórz", "Wejherowo", "Radomsko", "Skierniewice", "Starachowice", "Kutno", "Siedlce", "Nysa", "Mielec", "Piła", "Ostrów Wielkopolski", "Lubartów", "Jarosław", "Malbork", "Kraśnik", "Nowa Sól", "Zgierz", "Kołobrzeg", "Będzin", "Otwock", "Swarzędz", "Knurów", "Bochnia", "Świętochłowice", "Lębork"};
        for (String cityName : defaultCities) {
            cityRepository.save(new City(cityName));
        }
    }
}
