import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

public class RunesScraper {

    private static final Map<String, String> runesTranslationMap = new HashMap<>();

    static {
        // Traduções das ÁRVORES DE RUNAS
        runesTranslationMap.put("Domination", "Dominação");
        runesTranslationMap.put("Precision", "Precisão");
        runesTranslationMap.put("Sorcery", "Feitiçaria");
        runesTranslationMap.put("Resolve", "Determinação");
        runesTranslationMap.put("Inspiration", "Inspiração");

        // RUNAS PRINCIPAIS
        runesTranslationMap.put("Electrocute", "Eletrocutar");
        runesTranslationMap.put("Predator", "Predador");
        runesTranslationMap.put("Dark Harvest", "Colheita Sombria");
        runesTranslationMap.put("Hail of Blades", "Chuva de Lâminas");

        runesTranslationMap.put("Press the Attack", "Pressione o Ataque");
        runesTranslationMap.put("Lethal Tempo", "Ritmo Fatal");
        runesTranslationMap.put("Fleet Footwork", "Agilidade nos Pés");
        runesTranslationMap.put("Conqueror", "Conquistador");

        runesTranslationMap.put("Summon Aery", "Invocar Aery");
        runesTranslationMap.put("Arcane Comet", "Cometa Arcano");
        runesTranslationMap.put("Phase Rush", "Ímpeto Gradual");

        runesTranslationMap.put("Grasp of the Undying", "Aperto dos Mortos-Vivos");
        runesTranslationMap.put("Aftershock", "Pós-choque");
        runesTranslationMap.put("Guardian", "Guardião");

        runesTranslationMap.put("Glacial Augment", "Aprimoramento Glacial");
        runesTranslationMap.put("Unsealed Spellbook", "Livro de Feitiços Deslacrado");
        runesTranslationMap.put("First Strike", "Primeiro Ataque");

        // RUNAS SECUNDÁRIAS
        runesTranslationMap.put("Cheap Shot", "Golpe Desleal");
        runesTranslationMap.put("Taste of Blood", "Gosto de Sangue");
        runesTranslationMap.put("Sudden Impact", "Impacto Repentino");
        runesTranslationMap.put("Zombie Ward", "Sentinela Zumbi");
        runesTranslationMap.put("Ghost Poro", "Poro Fantasma");
        runesTranslationMap.put("Eyeball Collection", "Coleção de Globos Oculares");
        runesTranslationMap.put("Ravenous Hunter", "Caçador Voraz");
        runesTranslationMap.put("Ingenious Hunter", "Caçador Inventivo");
        runesTranslationMap.put("Relentless Hunter", "Caçador Incansável");
        runesTranslationMap.put("Ultimate Hunter", "Caçador Supremo");

        runesTranslationMap.put("Overheal", "Cura Excessiva");
        runesTranslationMap.put("Triumph", "Triunfo");
        runesTranslationMap.put("Presence of Mind", "Presença de Espírito");
        runesTranslationMap.put("Legend: Alacrity", "Lenda: Espontaneidade");
        runesTranslationMap.put("Legend: Tenacity", "Lenda: Tenacidade");
        runesTranslationMap.put("Legend: Bloodline", "Lenda: Linhagem");
        runesTranslationMap.put("Coup de Grace", "Golpe de Misericórdia");
        runesTranslationMap.put("Cut Down", "Dilacerar");
        runesTranslationMap.put("Last Stand", "Último Esforço");

        runesTranslationMap.put("Nullifying Orb", "Orbe Anulador");
        runesTranslationMap.put("Manaflow Band", "Faixa de Fluxo de Mana");
        runesTranslationMap.put("Nimbus Cloak", "Manto de Nimbus");
        runesTranslationMap.put("Transcendence", "Transcendência");
        runesTranslationMap.put("Celerity", "Celeridade");
        runesTranslationMap.put("Absolute Focus", "Foco Absoluto");
        runesTranslationMap.put("Scorch", "Chamuscar");
        runesTranslationMap.put("Waterwalking", "Caminhar Sobre as Águas");
        runesTranslationMap.put("Gathering Storm", "Tempestade Crescente");

        runesTranslationMap.put("Demolish", "Demolir");
        runesTranslationMap.put("Font of Life", "Fonte da Vida");
        runesTranslationMap.put("Shield Bash", "Golpe de Escudo");
        runesTranslationMap.put("Conditioning", "Condicionamento");
        runesTranslationMap.put("Second Wind", "Ventos Revigorantes");
        runesTranslationMap.put("Bone Plating", "Osso Revestido");
        runesTranslationMap.put("Overgrowth", "Crescimento Excessivo");
        runesTranslationMap.put("Revitalize", "Revitalizar");
        runesTranslationMap.put("Unflinching", "Inabalável");

        runesTranslationMap.put("Hextech Flashtraption", "Flashtraption Hextec");
        runesTranslationMap.put("Magical Footwear", "Calçados Mágicos");
        runesTranslationMap.put("Perfect Timing", "Cronômetro Perfeito");
        runesTranslationMap.put("Futures Market", "Mercado do Futuro");
        runesTranslationMap.put("Minion Dematerializer", "Desmaterializador de Tropas");
        runesTranslationMap.put("Biscuit Delivery", "Entrega de Biscoitos");
        runesTranslationMap.put("Cosmic Insight", "Perspicácia Cósmica");
        runesTranslationMap.put("Approach Velocity", "Velocidade de Aproximação");
        runesTranslationMap.put("Time Warp Tonic", "Tônico do Tempo");

        // RUNAS DE FRAGMENTOS
        runesTranslationMap.put("Adaptive Force", "Força Adaptativa");
        runesTranslationMap.put("Attack Speed", "Velocidade de Ataque");
        runesTranslationMap.put("Cooldown Reduction", "Redução de Tempo de Recarga");
        runesTranslationMap.put("Armor", "Armadura");
        runesTranslationMap.put("Magic Resist", "Resistência Mágica");
        runesTranslationMap.put("Health Scaling", "Vida Escalável");
    }


    public static String getRunes(String championName, String role) {
        try {

            String formattedName = championName.toLowerCase().replace(" ", "");

            String url = "https://br.op.gg/champions/" + formattedName + "/build";
            if (role != null && !role.isEmpty()) {
                url += "/" + role.toLowerCase();
            }
            url += "?type=ranked";

            // Conecta ao site e obtém o HTML
            Document document = Jsoup.connect(url).get();

            Elements runesElements = document.select(".rune-box-container--desktop .row .item img");

            if (runesElements.isEmpty()) {
                return "Nenhuma runa encontrada para o campeão " + championName + " na rota " + (role == null ? "mais utilizada" : role) + ". Verifique os dados.";
            }

            // Concatena as runas ativas
            StringBuilder runes = new StringBuilder();
            runes.append("Runas para ").append(championName);
            if (role != null && !role.isEmpty()) {
                runes.append(" na rota ").append(role);
            }
            runes.append(":\n\n");

            int index = 0; // Índice para identificar a posição da runa
            for (Element rune : runesElements) {
                String runeUrl = rune.attr("src");
                if (!runeUrl.contains("e_grayscale")) { // Apenas runas ativas
                    String runeName = rune.attr("alt"); // Nome da runa

                    runeName = runesTranslationMap.getOrDefault(runeName, runeName);

                    if (index == 5) {
                        runes.append("\n");
                    }

                    if (index == 0 || index == 5) {
                        runeName = runeName.toUpperCase();
                    }

                    runes.append("- ").append(runeName).append("\n");
                    index++;
                }
            }

            return runes.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao buscar as runas para " + championName + ". Verifique a conexão ou o nome do campeão.";
        }
    }
}
