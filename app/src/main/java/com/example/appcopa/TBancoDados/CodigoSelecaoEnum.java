package com.example.appcopa.TBancoDados; // Substitua pelo package do seu projeto

public enum CodigoSelecaoEnum {

    // === ANFITRIÕES ===
    CANADA("CAN", "Canadá"),
    ESTADOS_UNIDOS("USA", "Estados Unidos"),
    MEXICO("MEX", "México"),

    // === AFC (Ásia) ===
    ARABIA_SAUDITA("KSA", "Arábia Saudita"),
    AUSTRALIA("AUS", "Austrália"),
    CATAR("QAT", "Catar"),
    COREIA_DO_SUL("KOR", "Coreia do Sul"),
    IRA("IRN", "Irã"),
    IRAQUE("IRQ", "Iraque"),
    JAPAO("JPN", "Japão"),
    JORDANIA("JOR", "Jordânia"),
    UZBEQUISTAO("UZB", "Uzbequistão"),

    // === CAF (África) ===
    AFRICA_DO_SUL("RSA", "África do Sul"),
    ARGELIA("ALG", "Argélia"),
    CABO_VERDE("CPV", "Cabo Verde"),
    COSTA_DO_MARFIM("CIV", "Costa do Marfim"),
    EGIPTO("EGY", "Egito"),
    GANA("GHA", "Gana"),
    MARROCOS("MAR", "Marrocos"),
    RD_CONGO("COD", "RD do Congo"),
    SENEGAL("SEN", "Senegal"),
    TUNISIA("TUN", "Tunísia"),

    // === CONMEBOL (América do Sul) ===
    ARGENTINA("ARG", "Argentina"),
    BRASIL("BRA", "Brasil"),
    COLOMBIA("COL", "Colômbia"),
    EQUADOR("ECU", "Equador"),
    PARAGUAI("PAR", "Paraguai"),
    URUGUAI("URU", "Uruguai"),

    // === OFC (Oceânia) ===
    NOVA_ZELANDIA("NZL", "Nova Zelândia"),

    // === UEFA (Europa) ===
    ALEMANHA("GER", "Alemanha"),
    AUSTRIA("AUT", "Áustria"),
    BELGICA("BEL", "Bélgica"),
    BOSNIA_HERZEGOVINA("BIH", "Bósnia e Herzegovina"),
    CROACIA("CRO", "Croácia"),
    ESCOCIA("SCO", "Escócia"),
    ESPANHA("ESP", "Espanha"),
    FRANCA("FRA", "França"),
    HOLANDA("NED", "Holanda"),
    INGLATERRA("ENG", "Inglaterra"),
    NORUEGA("NOR", "Noruega"),
    PORTUGAL("POR", "Portugal"),
    REPUBLICA_TCHECA("CZE", "República Tcheca"),
    SUECIA("SWE", "Suécia"),
    SUICA("SUI", "Suíça"),
    TURQUIA("TUR", "Turquia"),

    // === CONCACAF (América do Norte, Central e Caribe) ===
    CURACAU("CUW", "Curaçau"),
    HAITI("HAI", "Haiti"),
    PANAMA("PAN", "Panamá");

    private final String codigoFifa;
    private final String nomeExibicao;

    // Construtor do ENUM
    CodigoSelecaoEnum(String codigoFifa, String nomeExibicao) {
        this.codigoFifa = codigoFifa;
        this.nomeExibicao = nomeExibicao;
    }

    public String getCodigoFifa() {
        return codigoFifa;
    }

    public String getNomeExibicao() {
        return nomeExibicao;
    }

    /**
     * Busca o ENUM com base na String de 3 letras da FIFA.
     * Exemplo: CodigoSelecaoEnum.deCodigoFifa("NED") retornará HOLANDA.
     */
    public static CodigoSelecaoEnum deCodigoFifa(String codigo) {
        for (CodigoSelecaoEnum selecao : values()) {
            if (selecao.getCodigoFifa().equalsIgnoreCase(codigo)) {
                return selecao;
            }
        }
        throw new IllegalArgumentException("Código FIFA não mapeado no sistema: " + codigo);
    }
}