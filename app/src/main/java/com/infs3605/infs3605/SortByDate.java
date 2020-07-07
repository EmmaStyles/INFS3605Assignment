package com.infs3605.infs3605;

import java.util.Collections;
import java.util.Comparator;

public class SortByDate implements Comparator<GeneralInfoArticle> {
    @Override
    public int compare(GeneralInfoArticle a, GeneralInfoArticle b) {
        return a.generalInfoDate.compareTo(b.getGeneralInfoDate());
    }
}
