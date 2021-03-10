set Food := { "Oatmeal", "Chicken", "Eggs", "Milk", "Pie", "Pork" };
set Nutrients := { "Energy", "Protein", "Calcium" };

param price[Food] = <"Oatmeal"> 17.5, <"Chicken"> 12, <"Eggs"> 11.2, <"Milk"> 9.7, <"Pie"> 11.2, <"Pork"> 19.1;
param target[Nutrients] := <"Protein"> 120.0, <"Energy"> 100.0, <"Calcium"> 150.0;

param content[Food*Nutrients] := <"Oatmeal","Energy"> 4.4,
                                 <"Oatmeal","Protein"> 0.2;

var x[Food] >= 0;

minimize fobj: sum <i> in F: price[i] * x[i];

subto targets: forall <j> in Nutrients:
    sum <i> in Food: content[i,j] * x[j] >= target[j];
    
