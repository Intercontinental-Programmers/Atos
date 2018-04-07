package com.ip.data.client;

import com.ip.domain.DeveloperRequest;

public class QueryStringSerializer {

    public static String serialize(DeveloperRequest request) {

        StringBuilder builder = new StringBuilder("?");
        builder.append("levels=");
        if (!request.getLevels().isEmpty()) {


            for (int i = 0; i < request.getLevels().size(); i++) {
                String level = request.getLevels().get(i);
                builder.append(level);


                if (i != request.getLevels().size() - 1)
                    builder.append(",");
            }

        }

        builder.append("&cities=");
        if (!request.getCities().isEmpty()) {


            for (int i = 0; i < request.getCities().size(); i++) {
                String city = request.getCities().get(i);
                builder.append(city);


                if (i != request.getCities().size() - 1)
                    builder.append(",");
            }

        }
        builder.append("&languages=");
        if (!request.getLanguages().isEmpty()) {

            for (int i = 0; i < request.getLanguages().size(); i++) {
                String city = request.getLanguages().get(i);
                builder.append(city);

                if (i != request.getLanguages().size() - 1)
                    builder.append(",");
            }
        }

        builder.append("&student=");
        if (request.isStudent() != null) {

            builder.append(request.isStudent());
        }

        return builder.toString();
    }
}
