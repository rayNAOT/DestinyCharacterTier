package com.example.raymond.destinycharactertier;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class TierActivity extends AppCompatActivity {
/*
* TODO
* Exception handling if user does not enter number in editText field - app currently closes
* Add buttons to INFO and ABOUT pages to direct users to other activities
* Update database to display data on separate activity with compare function
* Limit size of numbers that can be entered in editText fields
* Limit length of text length for class name
* Prevent users from saving data if no data is entered
* Issue with Ghost setOnEditorActionListener, function not changing focus to next editText element. Causing a return instead.
* */

    DatabaseHandler myDb;

    EditText   et_helm_intellect, et_helm_discipline, et_helm_strength,
            et_gauntlet_intellect, et_gauntlet_discipline, et_gauntlet_strength,
            et_chest_intellect, et_chest_discipline, et_chest_strength,
            et_legs_intellect, et_legs_discipline, et_legs_strength,
            et_class_intellect, et_class_discipline, et_class_strength,
            et_artifact_intellect, et_artifact_discipline, et_artifact_strength,
            et_ghost_intellect, et_ghost_discipline, et_ghost_strength,
            et_class_name;

    Button     btn_tier, btn_info, btn_about, btn_save, btn_database;

    TextView   tv_helm, tv_gauntlet, tv_chest, tv_legs, tv_class, tv_artifact, tv_ghost,
            tv_helm_roll, tv_gauntlet_roll, tv_chest_roll, tv_legs_roll, tv_class_roll, tv_artifact_roll, tv_ghost_roll,
            tv_total_intellect, tv_total_discipline, tv_total_strength,
            tv_tier_intellect, tv_tier_discipline, tv_tier_strength,
            tv_1;

    double result, rounded,
            helm_intellect, helm_discipline, helm_strength,
            gauntlet_intellect, gauntlet_discipline, gauntlet_strength,
            chest_intellect, chest_discipline, chest_strength,
            legs_intellect, legs_discipline, legs_strength,
            class_intellect, class_discipline, class_strength,
            artifact_intellect, artifact_discipline, artifact_strength,
            ghost_intellect, ghost_discipline, ghost_strength,
            intelTotal, disciplineTotal, strengthTotal,
            tierIntelTotal, tierDisciplineTotal, tierStrengthTotal, characterTier;

    Switch switch_helm, switch_gauntlet, switch_chest, switch_legs, switch_classItem, switch_artifact, switch_ghost;

    final int HELM_MAX = 46 + 46;
    final int GAUNTLET_MAX = 41 + 41;
    final int CHEST_MAX = 61 + 61;
    final int LEGS_MAX = 56 + 56;
    final int CLASS_MAX = 25 + 25;
    final int ARTIFACT_MAX = 38 + 38;
    final int GHOST_MAX = 25 + 25;

    final int HELM_BONUS = 19;
    final int GAUNTLET_BONUS = 17;
    final int CHEST_BONUS = 25;
    final int LEGS_BONUS = 23;
    final int CLASS_BONUS = 10;
    final int ARTIFACT_BONUS = 42;
    final int GHOST_BONUS = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i("Rewched here", " here " );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tier);

        myDb = new DatabaseHandler(this);

        et_helm_intellect = (EditText) findViewById(R.id.et_helm_intellect);
        et_helm_discipline = (EditText) findViewById(R.id.et_helm_discipline);
        et_helm_strength = (EditText) findViewById(R.id.et_helm_strength);

        et_gauntlet_intellect = (EditText) findViewById(R.id.et_gauntlet_intellect);
        et_gauntlet_discipline = (EditText) findViewById(R.id.et_gauntlet_discipline);
        et_gauntlet_strength = (EditText) findViewById(R.id.et_gauntlet_strength);

        et_chest_intellect = (EditText) findViewById(R.id.et_chest_intellect);
        et_chest_discipline = (EditText) findViewById(R.id.et_chest_discipline);
        et_chest_strength = (EditText) findViewById(R.id.et_chest_strength);

        et_legs_intellect = (EditText) findViewById(R.id.et_legs_intellect);
        et_legs_discipline = (EditText) findViewById(R.id.et_legs_discipline);
        et_legs_strength = (EditText) findViewById(R.id.et_legs_strength);

        et_class_intellect = (EditText) findViewById(R.id.et_class_intellect);
        et_class_discipline = (EditText) findViewById(R.id.et_class_discipline);
        et_class_strength = (EditText) findViewById(R.id.et_class_strength);

        et_artifact_intellect = (EditText) findViewById(R.id.et_artifact_intellect);
        et_artifact_discipline = (EditText) findViewById(R.id.et_artifact_discipline);
        et_artifact_strength = (EditText) findViewById(R.id.et_artifact_strength);

        et_ghost_intellect = (EditText) findViewById(R.id.et_ghost_intellect);
        et_ghost_discipline = (EditText) findViewById(R.id.et_ghost_discipline);
        et_ghost_strength = (EditText) findViewById(R.id.et_ghost_strength);

        et_class_name = (EditText) findViewById(R.id.et_class_name);

        btn_tier = (Button) findViewById(R.id.btn_tier);
        btn_info = (Button) findViewById(R.id.btn_info);
        btn_about = (Button) findViewById(R.id.btn_about);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_database = (Button) findViewById(R.id.btn_database);

        tv_helm = (TextView) findViewById(R.id.tv_helm);
        tv_gauntlet = (TextView) findViewById(R.id.tv_gauntlet);
        tv_chest = (TextView) findViewById(R.id.tv_chest);
        tv_legs = (TextView) findViewById(R.id.tv_legs);
        tv_class = (TextView) findViewById(R.id.tv_class);
        tv_artifact = (TextView) findViewById(R.id.tv_artifact);
        tv_ghost = (TextView) findViewById(R.id.tv_ghost);

        tv_helm_roll = (TextView) findViewById(R.id.tv_helm_roll);
        tv_gauntlet_roll = (TextView) findViewById(R.id.tv_gauntlet_roll);
        tv_chest_roll = (TextView) findViewById(R.id.tv_chest_roll);
        tv_legs_roll = (TextView) findViewById(R.id.tv_legs_roll);
        tv_class_roll = (TextView) findViewById(R.id.tv_class_roll);
        tv_artifact_roll = (TextView) findViewById(R.id.tv_artifact_roll);
        tv_ghost_roll = (TextView) findViewById(R.id.tv_ghost_roll);

        tv_total_intellect = (TextView) findViewById(R.id.tv_total_intellect);
        tv_total_discipline = (TextView) findViewById(R.id.tv_total_discipline);
        tv_total_strength = (TextView) findViewById(R.id.tv_total_strength);

        tv_tier_intellect = (TextView) findViewById(R.id.tv_tier_intellect);
        tv_tier_discipline = (TextView) findViewById(R.id.tv_tier_discipline);
        tv_tier_strength = (TextView) findViewById(R.id.tv_tier_strength);

        tv_1 = (TextView) findViewById(R.id.tv_1);

        switch_helm = (Switch) findViewById(R.id.switch_helm);
        switch_gauntlet = (Switch) findViewById(R.id.switch_gauntlet);
        switch_chest = (Switch) findViewById(R.id.switch_chest);
        switch_legs = (Switch) findViewById(R.id.switch_legs);
        switch_classItem = (Switch) findViewById(R.id.switch_classItem);
        switch_artifact = (Switch) findViewById(R.id.switch_artifact);
        switch_ghost = (Switch) findViewById(R.id.switch_ghost);

        AddData();
        viewAll();

        et_helm_intellect.setSelectAllOnFocus(true);

        et_helm_discipline.setSelectAllOnFocus(true);

        et_helm_strength.setSelectAllOnFocus(true);

        et_gauntlet_intellect.setSelectAllOnFocus(true);

        et_gauntlet_discipline.setSelectAllOnFocus(true);

        et_gauntlet_strength.setSelectAllOnFocus(true);

        et_chest_intellect.setSelectAllOnFocus(true);

        et_chest_discipline.setSelectAllOnFocus(true);

        et_chest_strength.setSelectAllOnFocus(true);

        et_legs_intellect.setSelectAllOnFocus(true);

        et_legs_discipline.setSelectAllOnFocus(true);

        et_legs_strength.setSelectAllOnFocus(true);

        et_class_intellect.setSelectAllOnFocus(true);

        et_class_discipline.setSelectAllOnFocus(true);

        et_class_strength.setSelectAllOnFocus(true);

        et_artifact_intellect.setSelectAllOnFocus(true);

        et_artifact_discipline.setSelectAllOnFocus(true);

        et_artifact_strength.setSelectAllOnFocus(true);

        et_ghost_intellect.setSelectAllOnFocus(true);

        et_ghost_discipline.setSelectAllOnFocus(true);

        et_ghost_strength.setSelectAllOnFocus(true);

        et_helm_intellect.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                upgradeHelm(v);
                return false;
            }
        });

        et_helm_discipline.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                upgradeHelm(v);
                return false;
            }
        });

        et_helm_strength.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                upgradeHelm(v);
                return false;
            }
        });

        et_gauntlet_intellect.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                upgradeGauntlet(v);
                return false;
            }
        });

        et_gauntlet_discipline.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                upgradeGauntlet(v);
                return false;
            }
        });

        et_gauntlet_strength.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                upgradeGauntlet(v);
                return false;
            }
        });

        et_chest_intellect.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                upgradeChest(v);
                return false;
            }
        });

        et_chest_discipline.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                upgradeChest(v);
                return false;
            }
        });

        et_chest_strength.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                upgradeChest(v);
                return false;
            }
        });

        et_legs_intellect.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                upgradeLegs(v);
                return false;
            }
        });

        et_legs_discipline.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                upgradeLegs(v);
                return false;
            }
        });

        et_legs_strength.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                upgradeLegs(v);
                return false;
            }
        });

        et_class_intellect.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                upgradeClassItem(v);
                return false;
            }
        });

        et_class_discipline.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                upgradeClassItem(v);
                return false;
            }
        });

        et_class_strength.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                upgradeClassItem(v);
                return false;
            }
        });

        et_artifact_intellect.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                upgradeArtifact(v);
                return false;
            }
        });

        et_artifact_discipline.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                upgradeArtifact(v);
                return false;
            }
        });

        et_artifact_strength.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                upgradeArtifact(v);
                return false;
            }
        });

        et_ghost_intellect.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                upgradeGhost(v);
                return false;
            }
        });

        et_ghost_discipline.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                upgradeGhost(v);
                return false;
            }
        });

        et_ghost_strength.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                upgradeGhost(v);
                return false;
            }
        });

        btn_tier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intelTotal = helm_intellect + gauntlet_intellect + chest_intellect + legs_intellect + class_intellect + artifact_intellect + ghost_intellect;
                disciplineTotal = helm_discipline + gauntlet_discipline + chest_discipline + legs_discipline + class_discipline + artifact_discipline + ghost_discipline;
                strengthTotal = helm_strength + gauntlet_strength + chest_strength + legs_strength + class_strength + artifact_strength + ghost_strength;
                tv_total_intellect.setText("" + intelTotal);
                tv_total_discipline.setText("" + disciplineTotal);
                tv_total_strength.setText("" + strengthTotal);

                tierIntelTotal = (double)Math.round(intelTotal / 60 * 10) / 10;
                tv_tier_intellect.setText("" + tierIntelTotal);

                tierDisciplineTotal = (double)Math.round(disciplineTotal / 60 * 10) / 10;
                tv_tier_discipline.setText("" + tierDisciplineTotal);

                tierStrengthTotal = (double)Math.round(strengthTotal / 60 * 10) / 10;
                tv_tier_strength.setText("" + tierStrengthTotal);

                characterTier = (double)Math.round((tierIntelTotal + tierDisciplineTotal + tierStrengthTotal) * 10) / 10;
                tv_1.setText("" + characterTier);

                if (characterTier >= 12) {
                    tv_1.setBackgroundColor(Color.parseColor("#ff33b5e5")); // holo_blue_light
                } else if (characterTier >= 10) {
                    tv_1.setBackgroundColor(Color.parseColor("#ff99cc00")); // holo_green_light
                } else if (characterTier >= 8) {
                    tv_1.setBackgroundColor(Color.parseColor("#ffffbb33")); // holo_orange_light
                } else if (characterTier >= 6) {
                    tv_1.setBackgroundColor(Color.parseColor("#ffff8800")); // holo_orange_dark
                } else {
                    tv_1.setBackgroundColor(Color.parseColor("#ffcc0000")); // holo_red_dark
                }
                Toast.makeText(getApplicationContext(), "Your Character Tier is: " + characterTier, Toast.LENGTH_SHORT).show();
            }
        });

        btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadInfo = new Intent(TierActivity.this, Info.class);
                startActivity(intentLoadInfo);
            }
        });

        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadAbout = new Intent(TierActivity.this, About.class);
                startActivity(intentLoadAbout);
            }
        });

    } // END onCreate class

    public void AddData() {
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(et_class_name.getText().toString(),
                        tv_total_intellect.getText().toString(),
                        tv_total_discipline.getText().toString(),
                        tv_total_strength.getText().toString(),
                        tv_1.getText().toString());
                if (isInserted = true) {
                    Toast.makeText(TierActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(TierActivity.this, "Data NOT Inserted", Toast.LENGTH_LONG).show();
                }
            }
        });
    } // END AddData class

    public void viewAll() {
        btn_database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if (res.getCount() == 0) {
                    // Show message
                    showMessage("Error", "No Data Found");
                    return;
                } else {
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("ID: " + res.getString(0) + "\n");
                        buffer.append("Class Name: " + res.getString(1) + "\n");
                        buffer.append("Total Intellect: " + res.getString(2) + "\n");
                        buffer.append("Total Discipline: " + res.getString(3) + "\n");
                        buffer.append("Total Strength: " + res.getString(4) + "\n");
                        buffer.append("Tier: " + res.getString(5) + "\n\n");
                    }
                    // Show all data
                    showMessage("Data", buffer.toString());
                }
            }
        });
    } // END viewAll class

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    } // END showMessage class

    public void upgradeHelm(View view) {
        helm_intellect = Integer.parseInt(et_helm_intellect.getText().toString());
        helm_discipline = Integer.parseInt(et_helm_discipline.getText().toString());
        helm_strength = Integer.parseInt(et_helm_strength.getText().toString());

        if (switch_helm.isChecked()) {
            Toast.makeText(getApplicationContext(), "HELM UPGRADED!", Toast.LENGTH_SHORT).show();
            result = (helm_intellect + helm_discipline + helm_strength - HELM_BONUS) / HELM_MAX * 100;
        } else {
            result = (helm_intellect + helm_discipline + helm_strength) / HELM_MAX * 100;
        }

        rounded = (double) Math.round(result * 10) / 10;
        tv_helm_roll.setText("" + rounded + " %");

        if (rounded >= 100) {
            tv_helm_roll.setBackgroundColor(Color.parseColor("#ff33b5e5")); // holo_blue_light
        } else if (rounded >= 95) {
            tv_helm_roll.setBackgroundColor(Color.parseColor("#ff99cc00")); // holo_green_light
        } else if (rounded >= 90) {
            tv_helm_roll.setBackgroundColor(Color.parseColor("#ffffbb33")); // holo_orange_light
        } else if (rounded >= 85) {
            tv_helm_roll.setBackgroundColor(Color.parseColor("#ffff8800")); // holo_orange_dark
        } else {
            tv_helm_roll.setBackgroundColor(Color.parseColor("#ffcc0000")); // holo_red_dark
        }
    } // END upgradeHelm class

    public void upgradeGauntlet(View view) {
        gauntlet_intellect = Integer.parseInt(et_gauntlet_intellect.getText().toString());
        gauntlet_discipline = Integer.parseInt(et_gauntlet_discipline.getText().toString());
        gauntlet_strength = Integer.parseInt(et_gauntlet_strength.getText().toString());

        if (switch_gauntlet.isChecked()) {
            Toast.makeText(getApplicationContext(), "GAUNTLET UPGRADED!", Toast.LENGTH_SHORT).show();
            result = (gauntlet_intellect + gauntlet_discipline + gauntlet_strength - GAUNTLET_BONUS) / GAUNTLET_MAX * 100;
        } else {
            result = (gauntlet_intellect + gauntlet_discipline + gauntlet_strength) / GAUNTLET_MAX * 100;
        }

        rounded = (double) Math.round(result * 10) / 10;
        tv_gauntlet_roll.setText("" + rounded + " %");

        if (rounded >= 100) {
            tv_gauntlet_roll.setBackgroundColor(Color.parseColor("#ff33b5e5")); // holo_blue_light
        } else if (rounded >= 95) {
            tv_gauntlet_roll.setBackgroundColor(Color.parseColor("#ff99cc00")); // holo_green_light
        } else if (rounded >= 90) {
            tv_gauntlet_roll.setBackgroundColor(Color.parseColor("#ffffbb33")); // holo_orange_light
        } else if (rounded >= 85) {
            tv_gauntlet_roll.setBackgroundColor(Color.parseColor("#ffff8800")); // holo_orange_dark
        } else {
            tv_gauntlet_roll.setBackgroundColor(Color.parseColor("#ffcc0000")); // holo_red_dark
        }
    } // END upgradeGauntlet class

    public void upgradeChest(View view) {
        chest_intellect = Integer.parseInt(et_chest_intellect.getText().toString());
        chest_discipline = Integer.parseInt(et_chest_discipline.getText().toString());
        chest_strength = Integer.parseInt(et_chest_strength.getText().toString());

        if (switch_chest.isChecked()) {
            Toast.makeText(getApplicationContext(), "CHEST UPGRADED!", Toast.LENGTH_SHORT).show();
            result = (chest_intellect + chest_discipline + chest_strength - CHEST_BONUS) / CHEST_MAX * 100;
        } else {
            result = (chest_intellect + chest_discipline + chest_strength) / CHEST_MAX * 100;
        }

        rounded = (double) Math.round(result * 10) / 10;
        tv_chest_roll.setText("" + rounded + " %");

        if (rounded >= 100) {
            tv_chest_roll.setBackgroundColor(Color.parseColor("#ff33b5e5")); // holo_blue_light
        } else if (rounded >= 95) {
            tv_chest_roll.setBackgroundColor(Color.parseColor("#ff99cc00")); // holo_green_light
        } else if (rounded >= 90) {
            tv_chest_roll.setBackgroundColor(Color.parseColor("#ffffbb33")); // holo_orange_light
        } else if (rounded >= 85) {
            tv_chest_roll.setBackgroundColor(Color.parseColor("#ffff8800")); // holo_orange_dark
        } else {
            tv_chest_roll.setBackgroundColor(Color.parseColor("#ffcc0000")); // holo_red_dark
        }
    } // END upgradeChest class

    public void upgradeLegs(View view) {
        legs_intellect = Integer.parseInt(et_legs_intellect.getText().toString());
        legs_discipline = Integer.parseInt(et_legs_discipline.getText().toString());
        legs_strength = Integer.parseInt(et_legs_strength.getText().toString());

        if (switch_legs.isChecked()) {
            Toast.makeText(getApplicationContext(), "LEGS UPGRADED!", Toast.LENGTH_SHORT).show();
            result = (legs_intellect + legs_discipline + legs_strength - LEGS_BONUS) / LEGS_MAX * 100;
        } else {
            result = (legs_intellect + legs_discipline + legs_strength) / LEGS_MAX * 100;
        }

        rounded = (double) Math.round(result * 10) / 10;
        tv_legs_roll.setText("" + rounded + " %");

        if (rounded >= 100) {
            tv_legs_roll.setBackgroundColor(Color.parseColor("#ff33b5e5")); // holo_blue_light
        } else if (rounded >= 95) {
            tv_legs_roll.setBackgroundColor(Color.parseColor("#ff99cc00")); // holo_green_light
        } else if (rounded >= 90) {
            tv_legs_roll.setBackgroundColor(Color.parseColor("#ffffbb33")); // holo_orange_light
        } else if (rounded >= 85) {
            tv_legs_roll.setBackgroundColor(Color.parseColor("#ffff8800")); // holo_orange_dark
        } else {
            tv_legs_roll.setBackgroundColor(Color.parseColor("#ffcc0000")); // holo_red_dark
        }
    } // END upgradeLegs class

    public void upgradeClassItem(View view) {
        class_intellect = Integer.parseInt(et_class_intellect.getText().toString());
        class_discipline = Integer.parseInt(et_class_discipline.getText().toString());
        class_strength = Integer.parseInt(et_class_strength.getText().toString());

        if (switch_classItem.isChecked()) {
            Toast.makeText(getApplicationContext(), "CLASS ITEM UPGRADED!", Toast.LENGTH_SHORT).show();
            result = (class_intellect + class_discipline + class_strength - CLASS_BONUS) / CLASS_MAX * 100;
        } else {
            result = (class_intellect + class_discipline + class_strength) / CLASS_MAX * 100;
        }

        rounded = (double)Math.round(result * 10) / 10;
        tv_class_roll.setText("" + rounded + " %");

        if (rounded >= 100) {
            tv_class_roll.setBackgroundColor(Color.parseColor("#ff33b5e5")); // holo_blue_light
        } else if (rounded >= 95) {
            tv_class_roll.setBackgroundColor(Color.parseColor("#ff99cc00")); // holo_green_light
        } else if (rounded >= 90) {
            tv_class_roll.setBackgroundColor(Color.parseColor("#ffffbb33")); // holo_orange_light
        } else if (rounded >= 85) {
            tv_class_roll.setBackgroundColor(Color.parseColor("#ffff8800")); // holo_orange_dark
        } else {
            tv_class_roll.setBackgroundColor(Color.parseColor("#ffcc0000")); // holo_red_dark
        }
    } // END upgradeClassItem class

    public void upgradeArtifact(View view) {
        artifact_intellect = Integer.parseInt(et_artifact_intellect.getText().toString());
        artifact_discipline = Integer.parseInt(et_artifact_discipline.getText().toString());
        artifact_strength = Integer.parseInt(et_artifact_strength.getText().toString());

        if (switch_artifact.isChecked()) {
            Toast.makeText(getApplicationContext(), "ARTIFACT UPGRADED!", Toast.LENGTH_SHORT).show();
            result = (artifact_intellect + artifact_discipline + artifact_strength - ARTIFACT_BONUS) / ARTIFACT_MAX * 100;
        } else {
            result = (artifact_intellect + artifact_discipline + artifact_strength) / ARTIFACT_MAX * 100;
        }

        rounded = (double) Math.round(result * 10) / 10;
        tv_artifact_roll.setText("" + rounded + " %");

        if (rounded >= 100) {
            tv_artifact_roll.setBackgroundColor(Color.parseColor("#ff33b5e5")); // holo_blue_light
        } else if (rounded >= 95) {
            tv_artifact_roll.setBackgroundColor(Color.parseColor("#ff99cc00")); // holo_green_light
        } else if (rounded >= 90) {
            tv_artifact_roll.setBackgroundColor(Color.parseColor("#ffffbb33")); // holo_orange_light
        } else if (rounded >= 85) {
            tv_artifact_roll.setBackgroundColor(Color.parseColor("#ffff8800")); // holo_orange_dark
        } else {
            tv_artifact_roll.setBackgroundColor(Color.parseColor("#ffcc0000")); // holo_red_dark
        }
    } // END upgradeArtifact class

    public void upgradeGhost(View view) {
        ghost_intellect = Integer.parseInt(et_ghost_intellect.getText().toString());
        ghost_discipline = Integer.parseInt(et_ghost_discipline.getText().toString());
        ghost_strength = Integer.parseInt(et_ghost_strength.getText().toString());

        if (switch_ghost.isChecked()) {
            Toast.makeText(getApplicationContext(), "GHOST UPGRADED!", Toast.LENGTH_SHORT).show();
            result = (ghost_intellect + ghost_discipline + ghost_strength - GHOST_BONUS) / GHOST_MAX * 100;
        } else {
            result = (ghost_intellect + ghost_discipline + ghost_strength) / GHOST_MAX * 100;
        }

        rounded = (double) Math.round(result * 10) / 10;
        tv_ghost_roll.setText("" + rounded + " %");

        if (rounded >= 100) {
            tv_ghost_roll.setBackgroundColor(Color.parseColor("#ff33b5e5")); // holo_blue_light
        } else if (rounded >= 95) {
            tv_ghost_roll.setBackgroundColor(Color.parseColor("#ff99cc00")); // holo_green_light
        } else if (rounded >= 90) {
            tv_ghost_roll.setBackgroundColor(Color.parseColor("#ffffbb33")); // holo_orange_light
        } else if (rounded >= 85) {
            tv_ghost_roll.setBackgroundColor(Color.parseColor("#ffff8800")); // holo_orange_dark
        } else {
            tv_ghost_roll.setBackgroundColor(Color.parseColor("#ffcc0000")); // holo_red_dark
        }
    } // END upgradeGhost class
} // END TierActivity superclass
