import com.google.gson.Gson;

import java.util.*;

public class MainApplication {
    public static void main(String[] args) {
        String parametro = "Just Desserts,-ice cream,-peanut";
        Optional<List> optionalIsbn = filterByType(parametro.split(",")[0]);

        String[] ingredientes = parametro.split(",");
        for (String ingrediente: ingredientes){
            if(ingrediente.contains("-")){
                optionalIsbn.get().removeIf(e -> ingrediente.replace("-", "").equals(e));
                continue;
            }

            if(ingrediente.contains("+")) {
                optionalIsbn.get().add(ingrediente.replace("+", ""));
                continue;
            }
        }


        System.out.println("Prato Final: ".concat(optionalIsbn.get().toString()));
    }

    private static Optional<List> filterByType(String typeDish) {
        Map<String, List> map = new Gson().fromJson(mockJson(), HashMap.class);
        return map.entrySet().stream()
                .filter(e -> typeDish.equals(e.getKey()))
                .map(Map.Entry::getValue)
                .findFirst();
    }

    public static String mockJson() {
        return "{\"Classic\":[\"strawberry\",\"banana\",\"pineapple\",\"mango\",\"peach\",\"honey\",\"ice\",\"yogurt\"]," +
                "\"Forest Berry\":[\"strawberry\",\"raspberry\",\"blueberry\",\"honey\",\"ice\",\"yogurt\"]," +
                "\"Freezie\":[\"blackberry\",\"blueberry\",\"black currant\",\"grape juice\",\"frozen yogurt\"]," +
                "\"Greenie\":[\"green apple\",\"kiwi\",\"lime\",\"avocado\",\"spinach\",\"ice\",\"apple juice\"]," +
                "\"Vegan Delite\":[\"strawberry\",\"passion fruit\",\"pineapple\",\"mango\",\"peach\",\"ice\",\"soy milk\"]," +
                "\"Just Desserts\":[\"banana\",\"ice cream\",\"chocolate\",\"peanut\",\"cherry\"]}";
    }
}
