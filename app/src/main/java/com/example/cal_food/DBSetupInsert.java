package com.example.cal_food;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.widget.Toast;

public class DBSetupInsert {

    /* Variables */
    private final Context context;

    /* Public Class ------------------------------------------------------ */
    public DBSetupInsert(Context ctx){
        this.context = ctx;
    }



    /* Setup Insert To Categories ----------------------------------------- */
    // To insert to category table
    public void setupInsertToCategories(String values){
        try{
            DBAdapter db = new DBAdapter(context);
            db.open();
            db.insert("categories",
                    "_id, category_name, category_parent_id, category_icon, category_note",
                    values);
            db.close();
        }
        catch (SQLiteException e){
            // Toast.makeText(context, "Error; Could not insert categories.", Toast.LENGTH_SHORT).show();
        }
    }
    public void insertAllCategories(){
        setupInsertToCategories("NULL, 'Bread', '0', '', NULL");
        setupInsertToCategories("NULL, 'Bread', '1', '', NULL");
        setupInsertToCategories("NULL, 'Cereal', '1', '', NULL");
        setupInsertToCategories("NULL, 'Frozen bread', '1', '', NULL");
        setupInsertToCategories("NULL, 'Crispbread', '1', '', NULL");

        // Parent id: 6
        setupInsertToCategories("NULL, 'Baked and snacks', '0', '', NULL");
        setupInsertToCategories("NULL, 'Baked', '6', '', NULL");
        setupInsertToCategories("NULL, 'Snacks', '6', '', NULL");


        setupInsertToCategories("NULL, 'Drink', '0', '', NULL");
        setupInsertToCategories("NULL, 'Soda', '9', '', NULL");


        setupInsertToCategories("NULL, 'Clean fruits and vegetables', '0', '', NULL");
        setupInsertToCategories("NULL, 'Frozen food', '11', '', NULL");
        setupInsertToCategories("NULL, 'Fruits', '11', '', NULL");
        setupInsertToCategories("NULL, 'Vegetables', '11', '', NULL");
        setupInsertToCategories("NULL, 'Canned food', '11', '', NULL");


        setupInsertToCategories("NULL, 'Functional foods', '0', '', NULL");
        setupInsertToCategories("NULL, 'Functional foods', '16', '', NULL");
        setupInsertToCategories("NULL, 'Protein bars', '16', '', NULL");
        setupInsertToCategories("NULL, 'Protein food', '16', '', NULL");


        setupInsertToCategories("NULL, 'Meat, chicken and fish', '0', '', NULL");
        setupInsertToCategories("NULL, 'Meat', '20', '', NULL");
        setupInsertToCategories("NULL, 'Chicken', '20', '', NULL");
        setupInsertToCategories("NULL, 'Fish', '20', '', NULL");


        setupInsertToCategories("NULL, 'Butter, eggs', '0', '', NULL");
        setupInsertToCategories("NULL, 'Eggs', '24', '', NULL");
        setupInsertToCategories("NULL, 'Butter', '24', '', NULL");
        setupInsertToCategories("NULL, 'Yogurt', '24', '', NULL");


        setupInsertToCategories("NULL, 'Dinner suggestions', '0', '', NULL");
        setupInsertToCategories("NULL, 'Fastfood', '28', '', NULL");
        setupInsertToCategories("NULL, 'Pizza', '28', '', NULL");
        setupInsertToCategories("NULL, 'Noodles', '28', '', NULL");
        setupInsertToCategories("NULL, 'Pho Noodles', '28', '', NULL");
        setupInsertToCategories("NULL, 'Rice', '28', '', NULL");
        setupInsertToCategories("NULL, 'Vegetarian food', '28', '', NULL");


        setupInsertToCategories("NULL, 'Cheese', '0', '', NULL");
        setupInsertToCategories("NULL, 'Cream cheese', '35', '', NULL");


        setupInsertToCategories("NULL, 'On bread', '0', '', NULL");
        setupInsertToCategories("NULL, 'Cold meats', '37', '', NULL");
        setupInsertToCategories("NULL, 'Sweet spreads', '37', '', NULL");
        setupInsertToCategories("NULL, 'Jam', '37', '', NULL");


        setupInsertToCategories("NULL, 'Snacks', '0', '', NULL");
        setupInsertToCategories("NULL, 'Nuts', '41', '', NULL");
        setupInsertToCategories("NULL, 'Potato chips', '41', '', NULL");
    }




    /* Setup Insert To Food ----------------------------------------------- */
    // To insert food to food table
    public void setupInsertToFood(String values){

        try {
            DBAdapter db = new DBAdapter(context);
            db.open();
            db.insert("food",
                    "_id, food_name, food_manufactor_name, food_serving_size_gram, food_serving_size_gram_mesurment, food_serving_size_pcs, food_serving_size_pcs_mesurment, food_energy, food_proteins, food_carbohydrates, food_fat, food_energy_calculated, food_proteins_calculated, food_carbohydrates_calculated, food_fat_calculated, food_user_id, food_barcode, food_category_id, food_thumb, food_image_a, food_image_b, food_image_c, food_notes",
                    values);
            db.close();
        }
        catch (SQLiteException e){
            // Toast.makeText(context, "Error; Could not insert food.", Toast.LENGTH_SHORT).show();
        }

    }
    // Insert all food into food database
    public void insertAllFood(){
        setupInsertToFood("NULL, 'Bread', 'Viet Nam', '26', 'gram', '1', 'oune', '266', '29.8', '208', '28.6', '66.5', '7.4', '51.9', '7.2', NULL, NULL, '2', 'aulie_speltlomper_med_havre_thumb.jpg', 'aulie_speltlomper_med_havre_a.jpg', 'aulie_speltlomper_med_havre_b.jpg', 'aulie_speltlomper_med_havre_c.jpg', NULL");
        setupInsertToFood("NULL, 'Milo Nestlé', 'Malaysia', '100', 'gram', '100', 'g', '363', '35', '50', '32', '363', '35', '50', '32', NULL, NULL, '3', 'axa_havregryn_lettkokt_thumb.jpg', 'axa_havregryn_lettkokt_a.jpg', 'axa_havregryn_lettkokt_b.jpg', 'axa_havregryn_lettkokt_c.jpg', NULL");
        setupInsertToFood("NULL, 'Calbee cereal', 'Nhat ban', '80', 'gram', '80', 'g', '350', '13', '61', '7', '350', '13', 61', '7', NULL, NULL, '3', 'axa_havregryn_store_thumb.jpg', 'axa_havregryn_store_a.jpg', 'axa_havregryn_store_b.jpg', 'axa_havregryn_store_c.jpg', NULL");
        setupInsertToFood("NULL, 'Grilled crab cake', 'Viet Nam', '100', 'gram', '1', 'package', '400', '0', '100', '0', '400', '0', '100', '0', NULL, NULL, '7', 'dan_sukker_sukker_thumb.jpg', 'dan_sukker_sukker_a.jpg', 'dan_sukker_sukker_b.jpg', 'dan_sukker_sukker_c.jpg', NULL");
        setupInsertToFood("NULL, 'Baked banh chung', 'Viet Nam', '100', 'gram', '1', 'package', '341', '10.2', '69.6', '1.6', '341', '10', '70', '2', NULL, NULL, '7', 'moollerens_siktet_hvetemel_thumb.jpg', 'moollerens_siktet_hvetemel_a.jpg', 'moollerens_siktet_hvetemel_b.jpg', 'moollerens_siktet_hvetemel_c.jpg', NULL");
        setupInsertToFood("NULL, 'Richy Cookies', 'Viet Nam', '200', 'gram', '1', 'package', '490', '7.8', '61', '23', '980', '16', '122', '46', NULL, NULL, '8', 'mondelez_norge_ritz_crackers_thumb.jpg', 'mondelez_norge_ritz_crackers_a.jpg', 'mondelez_norge_ritz_crackers_b.jpg', 'mondelez_norge_ritz_crackers_c.jpg', NULL");
        setupInsertToFood("NULL, 'Energy drink 7up', 'Viet Nam', '500', 'gram', '1', 'bottle', '50', '0.4', '11.5', '0', '250', '2', '58', '0', NULL, NULL, '10', 'ringnes_battery_energy_drink_50cl_thumb.jpg', 'ringnes_battery_energy_drink_50cl_a.jpg', 'ringnes_battery_energy_drink_50cl_b.jpg', 'ringnes_battery_energy_drink_50cl_c.jpg', NULL");
        setupInsertToFood("NULL, 'Sport +', 'Viet Nam', '16', 'gram', '1', 'stk', '338', '10.5', '55.5', '3.5', '54', '2', '9', '1', NULL, NULL, '5', 'wasa_sport_pluss_thumb.jpg', 'wasa_sport_pluss_a.jpg', 'wasa_sport_pluss_b.jpg', 'wasa_sport_pluss_c.jpg', NULL");
        setupInsertToFood("NULL, 'Sugar', 'Viet Nam', '100', 'gram', '100', 'gram', '400', '0', '100', '0', '400', '0', '100', '0', NULL, NULL, '7', 'dan_sukker_sukker_thumb.jpg', 'dan_sukker_sukker_a.jpg', 'dan_sukker_sukker_b.jpg', 'dan_sukker_sukker_c.jpg', NULL");
        setupInsertToFood("NULL, 'barley', 'Malaysia', '100', 'gram', '100', 'gram', '341', '10.2', '69.6', '1.6', '341', '10', '70', '2', NULL, NULL, '7', 'moollerens_siktet_hvetemel_thumb.jpg', 'moollerens_siktet_hvetemel_a.jpg', 'moollerens_siktet_hvetemel_b.jpg', 'moollerens_siktet_hvetemel_c.jpg', NULL");
        setupInsertToFood("NULL, 'Ritz Crackers', 'Malaysia', '200', 'gram', '1', 'pakke', '490', '7.8', '61', '23', '980', '16', '122', '46', NULL, NULL, '8', 'mondelez_norge_ritz_crackers_thumb.jpg', 'mondelez_norge_ritz_crackers_a.jpg', 'mondelez_norge_ritz_crackers_b.jpg', 'mondelez_norge_ritz_crackers_c.jpg', NULL");
        setupInsertToFood("NULL, 'Battery Energy Drink 50cl', 'Malaysia', '500', 'gram', '1', 'boks', '50', '0.4', '11.5', '0', '250', '2', '58', '0', NULL, NULL, '10', 'ringnes_battery_energy_drink_50cl_thumb.jpg', 'ringnes_battery_energy_drink_50cl_a.jpg', 'ringnes_battery_energy_drink_50cl_b.jpg', 'ringnes_battery_energy_drink_50cl_c.jpg', NULL");
        setupInsertToFood("NULL, 'Broccoli', 'Malaysia', '250', 'gram', '0.5', 'pose', '26', '1.9', '4.2', '0.2', '65', '5', '11', '1', NULL, NULL, '12', 'eldorado_frossen_brokkoliblanding_thumb.jpg', 'eldorado_frossen_brokkoliblanding_a.jpg', 'eldorado_frossen_brokkoliblanding_b.jpg', 'eldorado_frossen_brokkoliblanding_c.jpg', NULL");
        setupInsertToFood("NULL, 'Cabbage', 'Malaysia', '225', 'gram', '0.5', 'pose', '27', '2.8', '1.9', '0.5', '61', '6', '4', '1', NULL, NULL, '12', 'rema_1000_frosne_brokkolitopper_thumb.jpg', 'rema_1000_frosne_brokkolitopper_a.jpg', 'rema_1000_frosne_brokkolitopper_b.jpg', 'rema_1000_frosne_brokkolitopper_c.jpg', NULL");
        setupInsertToFood("NULL, 'Grapes', 'Japan', '200', 'gram', '0.5', 'pakke', '62', '0.7', '13.8', '0.2', '124', '1', '28', '0', NULL, NULL, '13', 'bama_rode_druer_thumb.jpg', 'bama_rode_druer_a.jpg', 'bama_rode_druer_b.jpg', 'bama_rode_druer_c.jpg', NULL");
        setupInsertToFood("NULL, 'Brokkoli', 'Japan', '300', 'gram', '0.5', 'stk', '33', '2.8', '7', '0.4', '99', '8', '21', '1', NULL, NULL, '14', 'bama_brokkoli_thumb.jpg', 'bama_brokkoli_a.jpg', 'bama_brokkoli_b.jpg', 'bama_brokkoli_c.jpg', NULL");
        setupInsertToFood("NULL, 'Gulrot', 'Japan', '44', 'gram', '1', 'stk', '41', '0.9', '10', '0.2', '18', '0', '4', '0', NULL, NULL, '14', 'bama_gulrot_thumb.jpg', 'bama_gulrot_a.jpg', 'bama_gulrot_b.jpg', 'bama_gulrot_c.jpg', NULL");
        setupInsertToFood("NULL, 'Salad', 'Korea', '125', 'gram', '0.5', 'pakke', '14', '0.8', '2.2', '0.1', '18', '1', '3', '0', NULL, NULL, '14', 'bama_isberg_mix_thumb.jpg', 'bama_isberg_mix_a.jpg', 'bama_isberg_mix_b.jpg', 'bama_isberg_mix_c.jpg', NULL");
       }


}
