package com.ltdd.calculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CalculatorScientificFragment extends Fragment {

    private Button btnNum0, btnNum1, btnNum2, btnNum3, btnNum4, btnNum5, btnNum6, btnNum7, btnNum8, btnNum9, btnPlus, btnMinus, btnDivide, btnMultiply, btnEqual, btnReverse, btnDel, btnClear, btnDot, btnPercent, btnBraceOpen, btnBraceClose, btnSin, btnCos, btnTan, btnMod, btnPow, btnRoot, btnPi, btnFactorial;
    private TextView txtResult, txtHistory;
    private boolean clearText, clearHistory, isBraced;
    private List<String> actionList;
    private int braceOpened;

    public CalculatorScientificFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calculator_scientific, container, false);

        Mapping(view);
        CreateBtnFunction();

        return view;
    }

    private void Mapping(View view) {
        btnNum0 = view.findViewById(R.id.btnNum0);
        btnNum1 = view.findViewById(R.id.btnNum1);
        btnNum2 = view.findViewById(R.id.btnNum2);
        btnNum3 = view.findViewById(R.id.btnNum3);
        btnNum4 = view.findViewById(R.id.btnNum4);
        btnNum5 = view.findViewById(R.id.btnNum5);
        btnNum6 = view.findViewById(R.id.btnNum6);
        btnNum7 = view.findViewById(R.id.btnNum7);
        btnNum8 = view.findViewById(R.id.btnNum8);
        btnNum9 = view.findViewById(R.id.btnNum9);
        btnPlus = view.findViewById(R.id.btnPlus);
        btnMinus = view.findViewById(R.id.btnMinus);
        btnDivide = view.findViewById(R.id.btnDivide);
        btnMultiply = view.findViewById(R.id.btnMultiply);
        btnPercent = view.findViewById(R.id.btnPercent);
        btnEqual = view.findViewById(R.id.btnEqual);
        btnClear = view.findViewById(R.id.btnClear);
        btnDel = view.findViewById(R.id.btnDel);
        btnDot = view.findViewById(R.id.btnDot);
        btnBraceOpen = view.findViewById(R.id.btnBracketOpen);
        btnBraceClose = view.findViewById(R.id.btnBracketClose);
        btnSin = view.findViewById(R.id.btnSin);
        btnCos = view.findViewById(R.id.btnCos);
        btnTan = view.findViewById(R.id.btnTan);
        btnMod = view.findViewById(R.id.btnMod);
        btnPow = view.findViewById(R.id.btnPow);
        btnRoot = view.findViewById(R.id.btnRoot);
        btnPi = view.findViewById(R.id.btnPi);
        btnFactorial = view.findViewById(R.id.btnFactorial);
        btnReverse = view.findViewById(R.id.btnReverse);
        txtHistory = view.findViewById(R.id.txtHistory);
        txtResult = view.findViewById(R.id.txtResult);
        clearText = clearHistory = true;
        isBraced = false;
        braceOpened = 0;
        actionList = new ArrayList<>();
    }

    private void CreateBtnFunction() {
        btnNum0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NumBtnPressed("0");
            }
        });
        btnNum1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NumBtnPressed("1");
            }
        });
        btnNum2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NumBtnPressed("2");
            }
        });
        btnNum3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NumBtnPressed("3");
            }
        });
        btnNum4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NumBtnPressed("4");
            }
        });
        btnNum5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NumBtnPressed("5");
            }
        });
        btnNum6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NumBtnPressed("6");
            }
        });
        btnNum7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NumBtnPressed("7");
            }
        });
        btnNum8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NumBtnPressed("8");
            }
        });
        btnNum9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NumBtnPressed("9");
            }
        });

        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clearText) {
                    txtResult.setText("");
                    clearText = false;
                }
                if (txtResult.getText().toString().equals("")) {
                    txtResult.append("0.");
                } else {
                    txtResult.append(".");
                }
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBraced) {
                    actionList.add("+");
                    String historyTextAppend = " + ";
                    txtHistory.append(historyTextAppend);
                    clearText = true;
                    isBraced = false;
                    return;
                }
                if (IsNull() || IsEditSymbol("+", "+")) return;
                FixDot();
                String resultText = txtResult.getText().toString();
                actionList.add(resultText);
                actionList.add("+");
                String historyTextAppend = resultText + " + ";
                txtHistory.append(historyTextAppend);
                clearText = true;
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBraced) {
                    actionList.add("-");
                    String historyTextAppend = " - ";
                    txtHistory.append(historyTextAppend);
                    clearText = true;
                    isBraced = false;
                    return;
                }
                if (IsNull() || IsEditSymbol("-", "-")) return;
                FixDot();
                String resultText = txtResult.getText().toString();
                actionList.add(resultText);
                actionList.add("-");
                String historyTextAppend = resultText + " - ";
                txtHistory.append(historyTextAppend);
                clearText = true;
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBraced) {
                    actionList.add("*");
                    String historyTextAppend = " × ";
                    txtHistory.append(historyTextAppend);
                    clearText = true;
                    isBraced = false;
                    return;
                }
                if (IsNull() || IsEditSymbol("*", "×")) return;
                FixDot();
                String resultText = txtResult.getText().toString();
                actionList.add(resultText);
                actionList.add("*");
                String historyTextAppend = resultText + " × ";
                txtHistory.append(historyTextAppend);
                clearText = true;
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBraced) {
                    actionList.add("/");
                    String historyTextAppend = " ÷ ";
                    txtHistory.append(historyTextAppend);
                    clearText = true;
                    isBraced = false;
                    return;
                }
                if (IsNull() || IsEditSymbol("/", "÷")) return;
                FixDot();
                String resultText = txtResult.getText().toString();
                actionList.add(resultText);
                actionList.add("/");
                String historyTextAppend = resultText + " ÷ ";
                txtHistory.append(historyTextAppend);
                clearText = true;
            }
        });

        btnMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBraced) {
                    actionList.add("mod");
                    String historyTextAppend = " mod ";
                    txtHistory.append(historyTextAppend);
                    clearText = true;
                    isBraced = false;
                    return;
                }
                if (IsNull() || IsEditSymbol("mod", "mod")) return;
                FixDot();
                String resultText = txtResult.getText().toString();
                actionList.add(resultText);
                actionList.add("mod");
                String historyTextAppend = resultText + " mod ";
                txtHistory.append(historyTextAppend);
                clearText = true;
            }
        });

        btnPow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBraced) {
                    actionList.add("^");
                    String historyTextAppend = " ^ ";
                    txtHistory.append(historyTextAppend);
                    clearText = true;
                    isBraced = false;
                    return;
                }
                if (IsNull() || IsEditSymbol("^", "^")) return;
                FixDot();
                String resultText = txtResult.getText().toString();
                actionList.add(resultText);
                actionList.add("^");
                String historyTextAppend = resultText + " ^ ";
                txtHistory.append(historyTextAppend);
                clearText = true;
            }
        });

        btnPi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clearHistory) {
                    txtHistory.setText("");
                    clearHistory = false;
                }
                if (clearText && !isBraced) {
                    actionList.add("π");
                    String historyTextAppend = "π";
                    txtHistory.append(historyTextAppend);
                    isBraced = true;
                }
            }
        });

        btnFactorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clearHistory) {
                    txtHistory.setText("");
                    clearHistory = false;
                }
                if (!isBraced && !IsNull()) {
                    String resultText = txtResult.getText().toString();
                    resultText = String.valueOf((int) Double.parseDouble(resultText));
                    actionList.add(resultText);
                    actionList.add("!");
                    String historyTextAppend = resultText + "!";
                    txtHistory.append(historyTextAppend);
                    isBraced = true;
                }
            }
        });

        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clearHistory) {
                    txtHistory.setText("");
                    clearHistory = false;
                }
                FixDot();
                if (isBraced) {
                    while (braceOpened > 0) {
                        actionList.add(")");
                        txtHistory.append(")");
                        braceOpened -= 1;
                    }
                    double result = Calculate(actionList);
                    if (result == -1) {
                        return;
                    }
                    actionList.clear();
                    String historyTextAppend = " = " + (result == (int) result ? Integer.toString((int) result) : result);
                    txtHistory.append(historyTextAppend);
                    String displayText = (result == (int) result ? Integer.toString((int) result) : result) + "";
                    txtResult.setText(displayText);
                    clearText = true;
                    clearHistory = true;
                    isBraced = false;
                    return;
                }

                if (IsNull()) return;
                String resultText = txtResult.getText().toString();
                actionList.add(resultText);
                txtHistory.append(resultText);
                while (braceOpened > 0) {
                    actionList.add(")");
                    txtHistory.append(")");
                    braceOpened -= 1;
                }
                double result = Calculate(actionList);
                if (result == -1) {
                    return;
                }
                actionList.clear();
                String historyTextAppend = " = " + (result == (int) result ? Integer.toString((int) result) : result);
                txtHistory.append(historyTextAppend);
                String displayText = (result == (int) result ? Integer.toString((int) result) : result) + "";
                txtResult.setText(displayText);
                clearText = true;
                clearHistory = true;
            }
        });

        btnPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int l = actionList.size();
                if (l > 0) {
                    double num = Double.parseDouble(actionList.get(l - 2));
                    double percent = Double.parseDouble(txtResult.getText().toString());
                    double result = num / 100 * percent;
                    txtResult.setText(result == (int) result ? Integer.toString((int) result) : result + "");
                }
            }
        });

        btnReverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = txtResult.getText().toString();
                if (text.toCharArray()[0] == '-')
                    txtResult.setText(text.substring(1));
                else {
                    String displayText = "-" + text;
                    txtResult.setText(displayText);
                }
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = txtResult.getText().toString();
                if (text.length() > 0) {
                    txtResult.setText(text.substring(0, text.length() - 1));
                    if (text.length() - 1 == 0) clearText = true;
                } else
                    clearText = true;
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionList.clear();
                txtResult.setText("");
                txtHistory.setText("");
                clearText = true;
                clearHistory = true;
                isBraced = false;
            }
        });

        btnBraceOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clearText && clearHistory) {
                    actionList.add("(");
                    txtHistory.setText("(");
                    clearHistory = false;
                    braceOpened += 1;
                    isBraced = false;
                } else if (clearText) {
                    actionList.add("(");
                    txtHistory.append("(");
                    braceOpened += 1;
                }
            }
        });

        btnBraceClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = txtHistory.getText().toString();
                String lastIndexObj = actionList.get(actionList.size() - 1);
                if (braceOpened > 0) {
                    if (text.toCharArray()[text.length() - 1] == '(') {
                        actionList.add("0");
                        actionList.add(")");
                        txtHistory.append("0)");
                        braceOpened -= 1;
                        isBraced = true;
                    } else if (lastIndexObj.equals("+") || lastIndexObj.equals("-") || lastIndexObj.equals("*") || lastIndexObj.equals("/")) {
                        FixDot();
                        String resultText = txtResult.getText().toString();
                        actionList.add(resultText);
                        actionList.add(")");
                        txtHistory.append(resultText + ")");
                        braceOpened -= 1;
                        isBraced = true;
                    } else {
                        actionList.add(")");
                        txtHistory.append(")");
                        braceOpened -= 1;
                        isBraced = true;
                    }
                }
            }
        });

        btnSin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clearHistory) {
                    txtHistory.setText("");
                    clearHistory = false;
                }
                if (isBraced) {
                    int indexLastBraceOpen = actionList.lastIndexOf("(");
                    if (indexLastBraceOpen == -1) return;
                    actionList.set(indexLastBraceOpen, "sin(");
                    String historyText = GetHistoryText(actionList);
                    txtHistory.setText(historyText);
                    return;
                }
                if (IsNull()) return;
                FixDot();
                String resultText = txtResult.getText().toString();
                actionList.add("sin(");
                actionList.add(resultText);
                actionList.add(")");
                String historyText = GetHistoryText(actionList);
                txtHistory.setText(historyText);
                txtResult.setText("");
                clearText = true;
                isBraced = true;
            }
        });

        btnCos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clearHistory) {
                    txtHistory.setText("");
                    clearHistory = false;
                }
                if (isBraced) {
                    int indexLastBraceOpen = actionList.lastIndexOf("(");
                    if (indexLastBraceOpen == -1) return;
                    actionList.set(indexLastBraceOpen, "cos(");
                    String historyText = GetHistoryText(actionList);
                    txtHistory.setText(historyText);
                    return;
                }
                if (IsNull()) return;
                FixDot();
                String resultText = txtResult.getText().toString();
                actionList.add("cos(");
                actionList.add(resultText);
                actionList.add(")");
                String historyText = GetHistoryText(actionList);
                txtHistory.setText(historyText);
                txtResult.setText("");
                clearText = true;
                isBraced = true;
            }
        });

        btnTan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clearHistory) {
                    txtHistory.setText("");
                    clearHistory = false;
                }
                if (isBraced) {
                    int indexLastBraceOpen = actionList.lastIndexOf("(");
                    if (indexLastBraceOpen == -1) return;
                    actionList.set(indexLastBraceOpen, "tan(");
                    String historyText = GetHistoryText(actionList);
                    txtHistory.setText(historyText);
                    return;
                }
                if (IsNull()) return;
                FixDot();
                String resultText = txtResult.getText().toString();
                actionList.add("tan(");
                actionList.add(resultText);
                actionList.add(")");
                String historyText = GetHistoryText(actionList);
                txtHistory.setText(historyText);
                txtResult.setText("");
                clearText = true;
                isBraced = true;
            }
        });

        btnRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clearHistory) {
                    txtHistory.setText("");
                    clearHistory = false;
                }
                if (isBraced) {
                    int indexLastBraceOpen = actionList.lastIndexOf("(");
                    if (indexLastBraceOpen == -1) return;
                    actionList.set(indexLastBraceOpen, "√(");
                    String historyText = GetHistoryText(actionList);
                    txtHistory.setText(historyText);
                    return;
                }
                if (IsNull()) return;
                FixDot();
                String resultText = txtResult.getText().toString();
                actionList.add("√(");
                actionList.add(resultText);
                actionList.add(")");
                String historyText = GetHistoryText(actionList);
                txtHistory.setText(historyText);
                txtResult.setText("");
                clearText = true;
                isBraced = true;
            }
        });
    }

    private double Calculate(List<String> actionList) {
        if (actionList.size() < 1) return -1;

        // Xử lý actionList
        for (int i = 0; i < actionList.size(); i++) {
            // Pi
            if (actionList.get(i).equals("π")) actionList.set(i, String.valueOf(Math.PI));
            // Giai thừa
            if (actionList.get(i).equals("!")) {
                int numToCalc = Integer.parseInt(actionList.get(i - 1));
                double result = 1;
                for (int j = 2; j <= numToCalc; j++) {
                    result *= j;
                }
                actionList.set(i - 1, String.valueOf(result));
                actionList.remove(i);
                i -= 1;
            }
        }

        while (true) {
            // Nếu có sin
            if (actionList.contains("sin(")) {
                int indexBraceOpen = actionList.indexOf("sin(");
                int indexBraceClose = -1;

                while (indexBraceClose == -1) {
                    for (int i = indexBraceOpen + 1; i < actionList.size(); i++) {
                        if (actionList.get(i).equals(")")) {
                            indexBraceClose = i;
                            List<String> tempActionList = actionList.subList(indexBraceOpen + 1, indexBraceClose);
                            Calculate(tempActionList);
                            tempActionList.set(0, String.valueOf(Sin(tempActionList.get(0))));
                            actionList.remove(indexBraceOpen + 2);
                            actionList.remove(indexBraceOpen);
                            break;
                        } else if (actionList.get(i).equals("sin(")) {
                            indexBraceOpen = i;
                            break;
                        }
                    }
                }
            }
            // Nếu có cos
            else if (actionList.contains("cos(")) {
                int indexBraceOpen = actionList.indexOf("cos(");
                int indexBraceClose = -1;

                while (indexBraceClose == -1) {
                    for (int i = indexBraceOpen + 1; i < actionList.size(); i++) {
                        if (actionList.get(i).equals(")")) {
                            indexBraceClose = i;
                            List<String> tempActionList = actionList.subList(indexBraceOpen + 1, indexBraceClose);
                            Calculate(tempActionList);
                            tempActionList.set(0, String.valueOf(Cos(tempActionList.get(0))));
                            actionList.remove(indexBraceOpen + 2);
                            actionList.remove(indexBraceOpen);
                            break;
                        } else if (actionList.get(i).equals("cos(")) {
                            indexBraceOpen = i;
                            break;
                        }
                    }
                }
            }
            // Nếu có tan
            else if (actionList.contains("tan(")) {
                int indexBraceOpen = actionList.indexOf("tan(");
                int indexBraceClose = -1;

                while (indexBraceClose == -1) {
                    for (int i = indexBraceOpen + 1; i < actionList.size(); i++) {
                        if (actionList.get(i).equals(")")) {
                            indexBraceClose = i;
                            List<String> tempActionList = actionList.subList(indexBraceOpen + 1, indexBraceClose);
                            Calculate(tempActionList);
                            tempActionList.set(0, String.valueOf(Tan(tempActionList.get(0))));
                            actionList.remove(indexBraceOpen + 2);
                            actionList.remove(indexBraceOpen);
                            break;
                        } else if (actionList.get(i).equals("tan(")) {
                            indexBraceOpen = i;
                            break;
                        }
                    }
                }
            }
            // Nếu có căn
            else if (actionList.contains("√(")) {
                int indexBraceOpen = actionList.indexOf("√(");
                int indexBraceClose = -1;

                while (indexBraceClose == -1) {
                    for (int i = indexBraceOpen + 1; i < actionList.size(); i++) {
                        if (actionList.get(i).equals(")")) {
                            indexBraceClose = i;
                            List<String> tempActionList = actionList.subList(indexBraceOpen + 1, indexBraceClose);
                            Calculate(tempActionList);
                            tempActionList.set(0, String.valueOf(Math.sqrt(Double.parseDouble(tempActionList.get(0)))));
                            actionList.remove(indexBraceOpen + 2);
                            actionList.remove(indexBraceOpen);
                            break;
                        } else if (actionList.get(i).equals("√(")) {
                            indexBraceOpen = i;
                            break;
                        }
                    }
                }
            }
            // Nếu có ngoặc
            else if (actionList.contains("(")) {
                int indexBraceOpen = actionList.indexOf("(");
                int indexBraceClose = -1;

                while (indexBraceClose == -1) {
                    for (int i = indexBraceOpen + 1; i < actionList.size(); i++) {
                        if (actionList.get(i).equals(")")) {
                            indexBraceClose = i;
                            List<String> tempActionList = actionList.subList(indexBraceOpen + 1, indexBraceClose);
                            Calculate(tempActionList);
                            actionList.remove(indexBraceOpen + 2);
                            actionList.remove(indexBraceOpen);
                            break;
                        } else if (actionList.get(i).equals("(")) {
                            indexBraceOpen = i;
                            break;
                        }
                    }
                }
            }
            // Nếu có mũ
            else if (actionList.contains("^")) {
                int indexPow = actionList.indexOf("^");
                double numA = Double.parseDouble(actionList.get(indexPow - 1));
                double numB = Double.parseDouble(actionList.get(indexPow + 1));
                double result = Math.pow(numA, numB);
                actionList.set(indexPow - 1, result + "");
                actionList.remove(indexPow + 1);
                actionList.remove(indexPow);
            }
            // Nếu nhân hoặc chia hoặc mod
            else if (actionList.contains("/") || actionList.contains("*") || actionList.contains("mod")) {
                int index = actionList.size();
                int indexMultiply = actionList.indexOf("*");
                int indexDivide = actionList.indexOf("/");
                int indexMod = actionList.indexOf("mod");

                if (indexMultiply > -1 && index > indexMultiply) index = indexMultiply;
                if (indexDivide > -1 && index > indexDivide) index = indexDivide;
                if (indexMod > -1 && index > indexMod) index = indexMod;

                // Nhân
                if (index == indexMultiply) {
                    double numA = Double.parseDouble(actionList.get(index - 1));
                    double numB = Double.parseDouble(actionList.get(index + 1));
                    double result = numA * numB;
                    actionList.set(index - 1, result + "");
                    actionList.remove(index + 1);
                    actionList.remove(index);
                }
                // Chia
                else if (index == indexDivide) {
                    double numA = Double.parseDouble(actionList.get(index - 1));
                    double numB = Double.parseDouble(actionList.get(index + 1));
                    double result = numA / numB;
                    actionList.set(index - 1, result + "");
                    actionList.remove(index + 1);
                    actionList.remove(index);
                }
                // Mod
                else if (index == indexMod) {
                    double numA = Double.parseDouble(actionList.get(index - 1));
                    double numB = Double.parseDouble(actionList.get(index + 1));
                    double result = numA % numB;
                    actionList.set(index - 1, result + "");
                    actionList.remove(index + 1);
                    actionList.remove(index);
                }
            }
            // Nếu cộng trừ
            else if (actionList.contains("+") || actionList.contains("-")) {
                int index = actionList.size();
                int indexPlus = actionList.indexOf("+");
                int indexMinus = actionList.indexOf("-");

                if (indexPlus > -1 && index > indexPlus) index = indexPlus;
                if (indexMinus > -1 && index > indexMinus) index = indexMinus;

                // Cộng
                if (index == indexPlus) {
                    double numA = Double.parseDouble(actionList.get(index - 1));
                    double numB = Double.parseDouble(actionList.get(index + 1));
                    double result = numA + numB;
                    actionList.set(index - 1, result + "");
                    actionList.remove(index + 1);
                    actionList.remove(index);
                }
                // Trừ
                else if (index == indexMinus) {
                    double numA = Double.parseDouble(actionList.get(index - 1));
                    double numB = Double.parseDouble(actionList.get(index + 1));
                    double result = numA - numB;
                    actionList.set(index - 1, result + "");
                    actionList.remove(index + 1);
                    actionList.remove(index);
                }
            } else break;
        }

        return Double.parseDouble(actionList.get(0));
    }

    private void FixDot() {
        String resultText = txtResult.getText().toString();
        if (!IsNull() && resultText.substring(resultText.length() - 1).equals(".")) {
            txtResult.append("0");
        }
    }

    private boolean IsEditSymbol(String symbol, String displayedSymbol) {
        if (clearHistory) {
            txtHistory.setText("");
            clearHistory = false;
        } else if (clearText && actionList.size() > 1) {
            actionList.set(actionList.size() - 1, symbol);
            String text = txtHistory.getText().toString();
            String displayText = text.substring(0, text.length() - 3) + " " + displayedSymbol + " ";
            txtHistory.setText(displayText);
            return true;
        }
        return false;
    }

    private void NumBtnPressed(String num) {
        if (clearText) {
            txtResult.setText("");
            clearText = false;
        }
        txtResult.append(num);
    }

    private boolean IsNull() {
        return txtResult.getText().toString().length() <= 0;
    }

    private String GetHistoryText(List<String> actionList) {
        String text = "";
        for (int i = 0; i < actionList.size(); i++) {
            String s = actionList.get(i);
            if (s.equals("+")) text += " + ";
            else if (s.equals("-")) text += " - ";
            else if (s.equals("*")) text += " × ";
            else if (s.equals("/")) text += " ÷ ";
            else if (s.equals("mod")) text += " mod ";
            else if (s.equals("^")) text += " ^ ";
            else text += s;
        }
        return text;
    }

    private double Sin(String num) {
        return Double.parseDouble(String.format(Locale.ENGLISH, "%.9f", Math.sin(Math.toRadians(Double.parseDouble(num)))));
    }

    private double Cos(String num) {
        return Double.parseDouble(String.format(Locale.ENGLISH, "%.9f", Math.cos(Math.toRadians(Double.parseDouble(num)))));
    }

    private double Tan(String num) {
        return Double.parseDouble(String.format(Locale.ENGLISH, "%.9f", Math.tan(Math.toRadians(Double.parseDouble(num)))));
    }
}
