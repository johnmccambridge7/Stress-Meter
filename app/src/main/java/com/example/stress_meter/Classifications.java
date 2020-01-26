package com.example.stress_meter;

import java.util.HashMap;
import java.util.Map;

public class Classifications {
    private Map<Integer, Integer> classifications;

    public Classifications() {
        classifications = new HashMap<Integer, Integer>();

        classifications.put(R.drawable.psm_alarm_clock, 5);
        classifications.put(R.drawable.psm_alarm_clock2, 6);
        classifications.put(R.drawable.psm_angry_face, 9);
        classifications.put(R.drawable.psm_anxious, 12);
        classifications.put(R.drawable.psm_baby_sleeping, 2);
        classifications.put(R.drawable.psm_bar, 1);
        classifications.put(R.drawable.psm_barbed_wire2, 12);
        classifications.put(R.drawable.psm_beach3, 1);
        classifications.put(R.drawable.psm_bird3, 3);
        classifications.put(R.drawable.psm_blue_drop, 2);
        classifications.put(R.drawable.psm_cat, 2);
        classifications.put(R.drawable.psm_clutter, 8);
        classifications.put(R.drawable.psm_clutter3, 9);
        classifications.put(R.drawable.psm_dog_sleeping, 2);
        classifications.put(R.drawable.psm_exam4, 10);
        classifications.put(R.drawable.psm_gambling4, 12);
        classifications.put(R.drawable.psm_headache, 14);
        classifications.put(R.drawable.psm_headache2, 14);
        classifications.put(R.drawable.psm_hiking3, 3);
        classifications.put(R.drawable.psm_kettle, 2);
        classifications.put(R.drawable.psm_lake3, 1);
        classifications.put(R.drawable.psm_lawn_chairs3, 3);
        classifications.put(R.drawable.psm_lonely, 12);
        classifications.put(R.drawable.psm_lonely2, 12);
        classifications.put(R.drawable.psm_mountains11, 2);
        classifications.put(R.drawable.psm_neutral_child, 5);
        classifications.put(R.drawable.psm_neutral_person2, 4);
        classifications.put(R.drawable.psm_peaceful_person, 2);
        classifications.put(R.drawable.psm_puppy, 1);
        classifications.put(R.drawable.psm_puppy3, 1);
        classifications.put(R.drawable.psm_reading_in_bed2, 1);
        classifications.put(R.drawable.psm_running3, 4);
        classifications.put(R.drawable.psm_running4, 4);
        classifications.put(R.drawable.psm_sticky_notes2, 6);
        classifications.put(R.drawable.psm_stressed_cat, 5);
        classifications.put(R.drawable.psm_stressed_person, 10);
        classifications.put(R.drawable.psm_stressed_person3, 9);
        classifications.put(R.drawable.psm_stressed_person4, 10);
        classifications.put(R.drawable.psm_stressed_person6, 12);
        classifications.put(R.drawable.psm_stressed_person7, 11);
        classifications.put(R.drawable.psm_stressed_person8, 12);
        classifications.put(R.drawable.psm_stressed_person12, 13);
        classifications.put(R.drawable.psm_talking_on_phone2, 9);
        classifications.put(R.drawable.psm_to_do_list, 9);
        classifications.put(R.drawable.psm_to_do_list3, 12);
        classifications.put(R.drawable.psm_wine3, 3);
        classifications.put(R.drawable.psm_yoga4, 1);
    }

    public int get(int classificationID) {
        if(this.classifications.keySet().contains(classificationID)) {
            return this.classifications.get(classificationID);
        }

        return -1;
    }
}
