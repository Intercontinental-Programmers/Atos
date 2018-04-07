package com.ip.data.client;

import com.ip.domain.DeveloperRequest;

public class QueryStringSerializer {

    private static boolean appendNext = false;

    public static String serialize(DeveloperRequest request){

        StringBuilder builder = new StringBuilder("?");

        if (!request.getLevels().isEmpty()){
            builder.append("levels=");

            for (int i = 0; i < request.getLevels().size(); i++){
                String level = request.getLevels().get(i);
                builder.append(level);


                if (i != request.getLevels().size() - 1)
                    builder.append(",");
            }

               appendNext = true;
        }

        if (!request.getCities().isEmpty()){

            if (appendNext)
                builder.append("&");

            builder.append("cities=");

            for (int i = 0; i < request.getCities().size(); i++){
                String city = request.getCities().get(i);
                builder.append(city);


                if (i != request.getCities().size() - 1)
                    builder.append(",");
            }

            appendNext = true;
        }

        if (!request.getLanguages().isEmpty()){

            if (appendNext)
                builder.append("&");

            builder.append("languages=");

            for (int i = 0; i < request.getLanguages().size(); i++){
                String city = request.getLanguages().get(i);
                builder.append(city);

                if (i != request.getLanguages().size() - 1)
                    builder.append(",");
            }

            appendNext = true;
        }

        if (request.isStudent() != null){
            if (appendNext)
                builder.append("&");

            builder.append("student=");

            builder.append(request.isStudent());
        }

        appendNext = false;
        return builder.toString();
    }
}
