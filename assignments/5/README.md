# HW 5 WordGen
Create “similar” text from input, based on character and word repetitions.

## Usage
```
$ java WordGen <text file path> [<starting string>] [<max length>]
```

## Design
I chose to implement both word and character prediction, by abstracting both into lists of strings. That way, for character prediction, all the strings would have a length of one, and with words they would have a variable length. Then in my `Table` and `FrequencyList` classes, I didn’t have to worry about which one I was doing, apart from in the `toString` methods, which needed to know so they could join the array of strings again.

I kept each of “prefixes” and “suffixes” as arrays of strings. This gave me the flexibility to change the prefix and suffix length at runtime, allowing experimentation of different combinations. For example, when generating the word predictions, I used a suffix of 1 and a prefix of 1, because with a prefix of 2 words there wasn’t enough flexibility.

## Testing
All tests, besides those using the CLI, are Junit 4.x compatible, in the `./test/` directory.

## Examples
### (Meta) Java Source
```
$ java WordGen src/WordGen.java
Generating with default arguments

im→p:100%
mp→o:100%
po→r:100%
or→p:49%,d:18%,t:11%,):9%, :7%,;:2%,.:2%,,:2%
rt→s:55%,i:18%, :12%,S:11%,,:3%,`:1%
t →s:29%,p:19%,j:14%,=:14%,+:10%,i:5%,a:5%,t:5%
 j→a:50%,o:50%
ja→v:100%
av→a:75%,e:25%
va→t:77%,.:23%
a.→u:67%,i:33%
.i→o:100%
io→n:75%,.:25%
o.→I:100%
.I→O:100%
IO→E:67%,.:33%
OE→x:100%
Ex→c:100%
xc→e:100%
ce→p:100%
ep→a:80%,t:20%
pt→i:100%
ti→n:58%,l:19%,c:12%,o:12%
on→ :40%,d:20%,s:20%,;:20%
n;→\n:100%
;\n→ :74%,\n:21%,i:5%
\ni→m:100%
.u→t:100%
ut→.:50%,s:38%,i:12%
il→s:38%,.:25%,e:25%,l:12%
l.→A:100%
.A→r:100%
Ar→r:100%
rr→a:100%
ra→t:61%,y:31%,c:6%, :3%
ay→s:55%,L:27%,(:18%
yL→i:100%
Li→s:100%
is→.:50%,t:36%,(:14%
st→a:53%,e:30%,<:7%,(:7%,;:3%
t;→\n:100%
ys→t:57%,.:36%,;:7%
s;→\n:100%
\n\n→ :76%,\n:16%,p:4%,i:4%
 s→t:39%,u:31%,p:19%,o:8%,w:3%
ta→r:70%,b:19%,t:11%
at→e:64%,o:22%,i:11%,\n:3%
ic→ :100%
c →W:30%,U:20%,S:20%,v:10%,c:10%,s:10%
 U→t:100%
Ut→i:100%
ls→.:75%,(:25%
s.→s:26%,c:22%,l:11%,t:7%,p:7%,a:7%,j:4%,P:4%,I:4%,m:4%,S:4%
.P→r:100%
Pr→e:100%
re→f:66%,t:17%,a:7%,c:3%, :3%,m:3%
ec→k:75%,o:25%
co→r:78%,p:17%,n:4%
nd→e:36%, :27%,):18%,;:9%,i:9%
di→t:100%
it→T:50%,h:14%,c:7%,i:7%,e:7%,(:7%,t:7%
ns→.:50%,t:50%
.c→o:71%,h:29%
ch→e:43%,a:29%, :14%,o:14%
he→c:33%, :22%,t:11%,r:11%,y:11%,n:11%
ck→N:100%
kN→o:100%
No→t:75%,w:25%
ot→N:100%
tN→u:100%
Nu→l:100%
ul→l:91%,t:9%
ll→ :38%,(:23%,;:23%,,:8%,):8%
l;→\n:100%
.S→t:100%
St→r:88%,a:12%
tr→i:95%,y:2%,a:2%
ri→n:83%,v:17%
in→g:63%,t:18%,p:6%,d:4%,S:2%,(:2%,;:1%,s:1%,n:1%
ng→ :27%,t:23%,S:16%,[:13%,,:7%,e:5%,>:2%,;:2%,s:1%,):1%,(:1%
gs→.:100%
.j→o:100%
jo→i:100%
oi→n:67%,d:33%
\np→u:100%
pu→s:63%,b:20%,t:17%
ub→l:88%,L:12%
bl→e:59%,i:41%
li→t:56%,c:44%
 c→o:57%,h:21%,a:14%,l:7%
cl→a:100%
la→s:100%
as→e:50%,s:25%,L:25%
ss→ :100%
s →=:50%,!:11%,o:11%,i:6%,I:6%,::6%,W:6%,):6%
 W→o:100%
Wo→r:100%
rd→G:88%,s:12%
dG→e:100%
Ge→n:100%
en→g:46%,e:32%,(:12%, :7%,t:2%
n →{:18%,j:18%,s:18%,w:9%,t:9%,":9%,e:9%,A:9%
 {→\n:100%
{\n→ :100%
\n → :100%
  → :85%,p:3%,S:3%,}:2%,t:2%,r:1%,i:1%,/:1%,s:1%,b:0%,w:0%,g:0%,c:0%,a:0%,m:0%,A:0%,W:0%,n:0%,f:0%,I:0%
 p→r:74%,u:18%,a:9%
pr→e:51%,i:49%
iv→a:100%
te→ :26%,d:21%,m:19%,g:12%,r:9%,T:7%,x:5%,a:2%
e →S:22%,t:17%,i:9%,I:4%,T:4%,2:4%,3:4%,=:4%,(:4%,v:4%,w:4%,c:4%,l:4%,b:4%,h:4%
 T→a:100%
Ta→b:100%
ab→l:100%
le→n:24%, :18%,T:18%,.:18%,(:12%,;:6%,t:6%
 t→h:60%,a:16%,o:12%,e:8%,r:4%
e;→\n:100%
 S→t:70%,y:30%
g →s:18%,c:18%,=:18%,p:9%,w:9%,g:9%,!:5%,t:5%,o:5%,j:5%
ar→t:84%,a:12%,s:1%,g:1%,e:1%
gS→t:100%
g;→\n:100%
 I→n:83%,O:17%
In→t:100%
nt→ :40%,e:20%,(:20%,l:16%,s:4%
eg→e:83%,i:17%
ge→n:48%,r:20%,t:16%,(:16%
er→a:61%, :22%,s:13%,.:4%
r →m:50%,=:12%,t:12%,c:12%,(:12%
 m→a:100%
ma→x:92%,i:8%
ax→P:100%
xP→a:100%
Pa→r:100%
ts→ :30%,.:21%,):15%,,:15%,[:9%,;:4%,(:4%,\:2%
 i→n:89%,t:6%,f:6%
ef→i:95%,a:5%
fi→x:100%
ix→L:45%,S:18%,E:15%,P:12%, :3%,(:3%,):3%
xL→e:100%
Le→n:100%
gt→h:100%
th→i:24%,;:19%, :16%,):11%,e:11%,,:8%,r:5%,]:3%,a:3%
h;→\n:100%
su→f:93%,b:7%
uf→f:100%
ff→i:100%
pa→r:100%
tS→e:100%
Se→p:100%
to→r:57%, :21%,A:14%,S:7%
r;→\n:100%
 /→/:100%
//→ :100%
/ →w:67%,g:33%
 g→e:100%
ne→r:57%,w:43%
ed→P:89%, :11%
d →a:25%,=:25%,m:12%,o:12%,f:12%,`:12%
 f→r:50%,o:50%
fr→o:100%
ro→m:50%,w:25%,u:25%
om→e:67%,F:17%, :17%
m →`:100%
 `→c:50%,p:50%
`c→o:100%
rp→u:100%
us→P:41%,,:23%,_:14%,):9%,(:9%,`:5%
s`→ :100%
` →a:100%
 a→n:50%,r:25%,l:12%,d:12%
an→g:44%,a:22%,d:11%,t:11%, :11%
`p→a:100%
t`→\n:100%
`\n→ :100%
g[→]:82%,p:9%,g:9%
[]→ :100%
] →s:44%,p:22%,n:11%,i:11%,c:11%
sP→a:100%
 v→o:100%
vo→i:100%
id→ :100%
ai→n:100%
n(→S:36%,":18%,c:18%,):18%,p:9%
(S→t:100%
np→u:100%
s)→ :44%,;:33%,):22%
) →{:75%,t:6%,::6%,<:6%,-:6%
hr→o:100%
ow→s:50%, :50%
ws→ :100%
s_→,:67%, :33%
_ →=:100%
 =→ :100%
= →n:29%,s:16%,i:10%,p:10%,c:6%,t:6%,A:6%,m:3%,g:3%,I:3%,0:3%,U:3%
O.→g:100%
.g→e:100%
et→u:45%,T:36%,h:9%,t:9%
tT→e:73%,a:27%
Te→x:100%
ex→t:81%, :10%,+:5%,;:5%
xt→(:53%,.:12%,):6%, :6%,F:6%,r:6%,,:6%,;:6%
tF→r:100%
Fr→o:100%
mF→i:100%
Fi→l:100%
e(→):40%,c:30%,p:10%,i:10%,s:10%
(i→n:100%
s[→1:50%,0:25%,2:25%
[0→]:100%
0]→):100%
])→;:75%,):25%
);→\n:97%, :3%
 n→u:54%,e:46%
nu→l:100%
sw→i:100%
wi→t:75%,l:25%
tc→h:100%
h →=:25%,d:12%,w:12%,(:12%,a:12%,-:12%,+:12%
 (→i:40%,n:20%,s:20%,p:20%
.l→e:100%
h)→;:75%, :25%
ca→s:100%
se→ :50%,I:25%,S:25%
 2→::50%,,:50%
2:→\n:100%
:\n→ :100%
[1→]:100%
1]→;:100%
];→\n:100%
 b→r:67%,e:33%
br→e:100%
ea→k:67%,d:33%
ak→;:100%
k;→\n:100%
 3→::100%
3:→\n:100%
r.→p:50%,e:50%
.p→r:82%,a:18%
rs→e:25%,\:25%, :25%,\n:25%
eI→n:100%
t(→):35%,w:24%,i:6%,c:6%,s:6%,n:6%,g:6%,S:6%,p:6%
[2→]:100%
2]→):100%
 }→\n:100%
}\n→\n:69%, :23%,}:8%
Sy→s:100%
em→.:89%,o:11%
m.→o:100%
.o→u:100%
ou→t:89%,g:11%
t.→p:80%,s:10%,l:10%
tl→n:100%
ln→(:100%
("→G:33%,\:33%,":33%
"G→e:100%
 w→i:25%,h:25%, :17%,e:17%,o:8%,a:8%
 d→e:100%
de→x:80%,f:20%
fa→u:100%
au→l:100%
lt→ :100%
rg→u:100%
gu→m:100%
um→e:100%
me→C:40%,P:40%,n:20%
s\→n:100%
\n→":50%,\:25%,N:25%
n"→):100%
")→;:80%,):20%
w →=:22%,W:22%,S:22%,T:11%,A:11%,t:11%
ew→ :60%,P:40%
(c→o:89%,h:11%
_,→ :100%
, →s:26%,1:11%,m:9%,S:9%,p:9%,":6%,I:6%,i:6%,n:6%,w:6%,2:3%,g:3%,0:3%
g,→ :100%
(w→.:100%
w.→g:100%
eT→e:100%
()→):31%,;:31%, :31%,]:6%
))→;:78%,.:11%, :11%
"\→n:100%
n\→n:100%
nN→o:100%
ry→i:100%
yi→n:100%
wo→r:100%
ds→ :100%
ad→d:75%, :25%
 o→f:50%,r:25%,n:25%
of→ :100%
f →c:33%,t:33%,(:33%
ha→r:50%,v:25%,t:25%
ac→t:100%
ct→e:100%
s,→ :100%
 1→,:100%
1,→ :100%
 "→":60%,):20%, :20%
" →":50%,t:50%
hi→s:90%,l:10%
s(→c:29%,):29%,g:14%,":14%,S:14%
l,→ :100%
l)→;:100%
2,→ :100%
""→):50%,,:25%, :25%
h,→ :100%
r)→;:75%, :25%
.t→o:75%,a:25%
(p→a:71%,r:29%
.s→i:50%,u:25%,t:12%,p:12%
l(→p:33%,c:33%,A:33%
sp→l:100%
pl→i:100%
.m→a:100%
 !→=:100%
!=→ :100%
l →?:40%,&:20%,p:20%,a:20%
 ?→ :100%
? →m:50%,s:50%
 :→ :100%
: →c:50%,A:50%
na→l:100%
al→y:50%,l:25%,s:25%
ly→z:100%
yz→e:100%
ze→(:67%,C:33%
eC→o:100%
Co→r:100%
 r→e:100%
tu→r:100%
ur→n:100%
rn→ :100%
e.→t:33%,c:33%,a:33%
oS→t:100%
g(→):100%
 A→r:100%
t<→S:100%
<S→t:100%
g>→ :50%,(:50%
> →g:100%
dP→a:100%
>(→):100%
wP→a:100%
(s→t:33%,u:33%,p:33%
g)→ :100%
op→y:100%
py→O:100%
yO→f:100%
Of→R:100%
fR→a:100%
Ra→n:100%
 0→,:50%,;:50%
0,→ :100%
wh→e:67%,i:33%
(n→e:100%
 &→&:100%
&&→ :100%
& → :100%
si→z:100%
iz→e:100%
 <→ :50%,=:50%
< →m:100%
.a→d:67%,s:33%
dd→A:33%,(:33%, :33%
dA→l:100%
Al→l:100%
(A→r:100%
sL→i:100%
x →=:67%,<:33%
bL→i:100%
(g→e:100%
 -→ :100%
- →p:50%,(:50%
).→t:100%
oA→r:100%
y(→n:100%
[p→r:100%
h]→):100%
ho→o:100%
oo→s:100%
os→e:100%
eS→u:100%
Su→f:100%
x(→p:100%
x)→;:100%
nS→o:100%
So→m:100%
[g→e:100%
)]→):100%
we→ :100%
wa→n:100%
o →i:25%,t:25%,w:25%,r:25%
ug→h:100%
gh→ :100%
ey→ :100%
y →a:100%
 l→e:100%
tt→e:50%,i:50%
s\n→ :100%
fo→r:100%
0;→ :100%
; →i:100%
<=→ :100%
 +→ :100%
+ →p:67%,s:33%
x+→+:100%
++→):100%
+)→ :100%
xS→t:100%
x;→\n:100%
xE→n:100%
En→d:100%
d;→\n:100%
t,→ :100%
d)→;:100%
d(→p:100%
t)→ :100%
",→ :100%
 e→x:100%
a →":100%
be→g:100%
gi→n:100%
nn→i:100%
ni→n:100%
so→m:67%, :33%
 h→a:100%
ve→ :100%
mo→v:100%
ov→e:100%
t\n→ :100%
if→ :100%
.e→q:100%
eq→u:100%
qu→a:100%
ua→l:100%
eP→a:100%
 )→ :100%
r,→ :100%

imporpus_, ing, splitegenew Worpublithis.corpus_, start, getText.prefixLen {
      prind);

   }

              cor maxParts ing stableng geng != tablengthistablenew Table.corts.maxPartSeparts.PrefixLen(StrivatedPart, maxParts, Int + prin();
      prefixLen "");
                       pusPart pring[] splic voing, ingStrins.preak;


         starts = maxParateText.suffixParts = Arrays.suffixEndext(w.geturn "");
            this.le Strivate bre brefixString != null(paratex = null ? maxParts : cordGength = null, maxParts = preckNotNull(parts, witText.parts Wor tartileTextrinSomeParts = ing prefixLen(String[] prefixParts.starts.size()).to we witText(gerseInt());
    tarts);
             split(String gen("\n");
          we Tablitingth, splitText();
    }

     inpusPartilengength;

  prefixEnd andext, stedParting[] st(w.gengs.joingtheckNotNull ? maxParts = theckNotNull ? maxPart javatorpuble.copyOfRang ge();

       antegerse this.privatedPartion "");
       }

                   prefixLenewParts ing suffixParts;
       }

    String pringth;
         }

    }

                           }

          ingthis.sizeCorpusParts.maxParts("Geng state String suffixStartils.corts of copyOfRante Sys.sizeCordGeng, ingth - prefixEnd = Int(w.gen {
       reak;
  reak;

  thistablitText();
                geng[] ing> gengthe ingth dext());



     int starate String = Intln(String cortinSomFils.add(parts = ArrategengStrin(String != null;
     brefixStrivategeratinpus, ");

       chartils.le(corts) {
            String[] null ? maxPart joid maxParts.IOExcepartSepartSeparts;
       // worpus) this.addAll;
  }

                                       this.cordGengthey ang[] starts.addAll(corpusParts = nerayLis.len ext.pring gerate Systarts(corpus, starts : corpuble IntedParts = maxParts() {
   ind angStablitTex++) < maxPart + pretText.prefixString[] newParts.pringe(cor);
              String w Strintln("Gengthrows WordGength = ings.to ingenerayste voin;
   geturn suffixLeng = newParts.suffixStrint join() {
    ingth;
       we (starts("\n");

                                Strin(corpusPart pringth;


         // whey alyze(corput.preturn joid = ing;
   SystedPartingStrivatem.out.pringStringth;
        ithistablic String = ingen("\n");
    th = 0, stem.outs.size(ing gen "");
      Wor maxPartilen w Arrato we thiste 2:
           starts = neratedPart, maxParts;
       Strind alyze(int();
        reak;
  starts of case String = null;
   }

        }

                              String[] starts.cor;
          theckNotNull ? maxPartSeparategenewParate (inpusPartSeparatorpus) {
  publeText();
     refixParatoArrays.lenew = maxPars ordGenew = suffixEnd maxPart, ing[] publitText(w.geng suffixLen ext() {
               }

             th, suffixParts(geturn {
    Sys.size() {
           th);

                   Stringth andext.pringth;

   refixString, startSeparayst<Strivatintem.outs);

   analyze(cor);
                   thistablenew Arraystarts) {
          System.out.prefix(part javatem.out.stable.torpus_, 1, preak;
      te tem.out.prefixLen(Strin(Stringthis.size();
        Systart, SystableTex++) {
           prefixEndext() {
     }

                inpus, splic Worting[] stem.outil.Arrate 3:
  StringStringString[] suffixLen("\n");
   Startinte corpublen(Stringth;
    suffixLeng of corts = IntedParts.add(partind maxPartils.torpusPartind;

   Strivatort = null(parts, suffixStrint + prefixLenew Stringen javategeng;
        Statem.outs.size();
    preturn someParatorput.statorts) {
     ch (ing splitText.size();
            case ing copyOfRangStrint prinSomeCorput.prefixLenew = ing;
  retTable.torpusParate 3:
     String, splitText(StringtheckNotNull(preturn ext(StringthetText, we (suffixStringString, witText(cortSeparts = null, suffixEnd frows = pringen("Generaystart suffixParts, "");
                     refixLener maxParts != cordGenew = cor maxParts of th;
  corpus) : checkNotNull ? maxPart stext(w.gen(Stable th dext, maxPartileneratingStarts WordGenerator);

                  // withis.size()).toArrate 3:
           Stringthis.size() th;
       splic Utinpus_, S

Now trying with words instead of characters

import→java.io.IOException;\nimport:100%
java.io.IOException;\nimport→java.util.ArrayList;\nimport:100%
java.util.ArrayList;\nimport→java.util.Arrays;\n\nimport:100%
java.util.Arrays;\n\nimport→static:100%
static→Utils.Preconditions.checkNotNull;\nimport:33%,Utils.Strings.join;\n\n\npublic:33%,void:33%
Utils.Preconditions.checkNotNull;\nimport→static:100%
Utils.Strings.join;\n\n\npublic→class:100%
class→WordGen:100%
WordGen→{\n:50%,w:50%
{\n→:100%
→:85%,private:2%,}\n\n:1%,public:1%,return:1%,String[]:1%,int:1%,}\n:1%,//:1%,break;\n:0%,case:0%,System.out.print(w.getTableText());\n:0%,System.out.println();\n:0%,System.out.print(w.generateText());\n\n:0%,startingString:0%,this(corpus,:0%,String:0%,}\n\n\n:0%,w:0%,maxParts:0%,System.out.println("Generating:0%,this.table:0%,this.startingString:0%,this.prefixLength:0%,this.suffixLength:0%,this.partSeparator:0%,this.corpusParts:0%,this.maxParts:0%,analyzeCorpus();\n:0%,WordGen:0%,ArrayList<String>:0%,Integer:0%,while:0%,generatedParts.size():0%,generatedParts.addAll(Arrays.asList(newParts));\n\n:0%,newParts:0%,System.out.println("\n\nNow:0%,for:0%,switch:0%,table.add(prefixParts,:0%,if:0%,}\n}:0%
private→String:30%,int:20%,String[]:20%,Table:10%,Integer:10%,void:10%
Table→table;\n:100%
table;\n→:100%
String→startingString,:20%,partSeparator;\n\n\n:10%,corpus_:10%,startingString:10%,startingString;\n:10%,partSeparator):10%,getTableText():10%,generateText():10%,joinSomeCorpusParts(String[]:10%
startingString;\n→:100%
Integer→maxParts;\n:25%,maxParts:25%,maxParts):25%,maxParts,:25%
maxParts;\n→:100%
int→prefixLength;\n:12%,suffixLength;\n:12%,prefixLength,:12%,suffixLength,:12%,prefixStart:12%,prefixEnd:12%,suffixStart:12%,suffixEnd:12%
prefixLength;\n→:100%
suffixLength;\n→:100%
partSeparator;\n\n\n→:100%
//→generated:33%,we:33%,when:33%
generated→from:100%
from→`corpus`:100%
`corpus`→and:100%
and→`part`\n:100%
`part`\n→:100%
String[]→corpusParts;\n\n:14%,newParts:14%,prefix:14%,prefixParts:14%,suffixParts:14%,splitText(String:14%,splitText:14%
corpusParts;\n\n→:100%
public→WordGen(String:50%,String:33%,static:17%
void→main(String[]:50%,analyzeCorpus():50%
main(String[]→inputs):100%
inputs)→throws:100%
throws→IOException:100%
IOException→{\n:100%
corpus_→=:100%
=→new:15%,null;\n:7%,inputs[1];\n:7%,Arrays.copyOfRange(corpusParts,:7%,Utils.IO.getTextFromFile(inputs[0]);\n:4%,startingString;\n:4%,prefixLength;\n:4%,suffixLength;\n:4%,checkNotNull(partSeparator);\n:4%,splitText(checkNotNull(corpus));\n:4%,maxParts:4%,startingString:4%,generatedParts.subList(generatedParts.size():4%,table.chooseSuffix(prefix);\n:4%,0;:4%,index;\n:4%,prefixStart:4%,prefixEnd;\n:4%,suffixStart:4%,Integer.parseInt(inputs[2]);\n:4%,text.split(partSeparator);\n\n:4%
Utils.IO.getTextFromFile(inputs[0]);\n→:100%
startingString→=:75%,!=:25%
null;\n→:100%
maxParts→=:50%,!=:25%,::25%
switch→(inputs.length):100%
(inputs.length)→{\n:100%
case→2:\n:50%,3:\n:50%
2:\n→:100%
inputs[1];\n→:100%
break;\n→:100%
3:\n→:100%
Integer.parseInt(inputs[2]);\n→:100%
}\n→:100%
System.out.println("Generating→with:100%
with→default:50%,words:50%
default→arguments\n");\n:100%
arguments\n");\n→:100%
w→=:100%
new→WordGen(corpus_,:50%,Table(partSeparator);\n:25%,ArrayList<String>();\n\n:25%
WordGen(corpus_,→startingString,:100%
startingString,→maxParts,:40%,Integer:40%,maxParts);\n:20%
maxParts);\n→:100%
System.out.print(w.getTableText());\n→:100%
System.out.println();\n→:100%
System.out.print(w.generateText());\n\n→:100%
System.out.println("\n\nNow→trying:100%
trying→with:100%
words→instead:100%
instead→of:100%
of→characters\n");\n:50%,the:50%
characters\n");\n→:100%
maxParts,→1,:33%,2,:33%,int:33%
1,→1,:25%,":25%,"");\n:25%,splitText.length);\n:25%
"→");\n:100%
");\n→:100%
}\n\n→:100%
WordGen(String→corpus,:67%,corpus):33%
corpus)→{\n:100%
this(corpus,→null,:50%,startingString,:50%
null,→null);\n:100%
null);\n→:100%
corpus,→String:67%,whether:33%
maxParts)→{\n:100%
2,→1,:100%
"");\n→:100%
prefixLength,→int:50%,generatedParts.size()).toArray(new:50%
suffixLength,→String:100%
partSeparator)→{\n:100%
this.table→=:100%
Table(partSeparator);\n→:100%
this.startingString→=:100%
this.prefixLength→=:100%
this.suffixLength→=:100%
this.partSeparator→=:100%
checkNotNull(partSeparator);\n→:100%
this.corpusParts→=:100%
splitText(checkNotNull(corpus));\n→:100%
this.maxParts→=:100%
!=→null:100%
null→?:67%,&&:33%
?→maxParts:50%,splitText(startingString):50%
:→corpusParts.length;\n:50%,Arrays.copyOfRange(corpusParts,:50%
corpusParts.length;\n→:100%
analyzeCorpus();\n→:100%
getTableText()→{\n:100%
return→table.toString();\n:20%,joinSomeCorpusParts(generatedParts.toArray(new:20%,Arrays.copyOfRange(splitText,:20%,splitText;\n:20%,join(partSeparator,:20%
table.toString();\n→:100%
generateText()→{\n:100%
ArrayList<String>→generatedParts:100%
generatedParts→=:100%
ArrayList<String>();\n\n→:100%
newParts→=:100%
splitText(startingString)→::100%
Arrays.copyOfRange(corpusParts,→0,:33%,prefixStart,:33%,suffixStart,:33%
0,→prefixLength);\n\n:100%
prefixLength);\n\n→:100%
while→(newParts:100%
(newParts→!=:100%
&&→:100%
generatedParts.size()→<:100%
<→maxParts):100%
generatedParts.addAll(Arrays.asList(newParts));\n\n→:100%
prefix→=:100%
generatedParts.subList(generatedParts.size()→-:100%
-→prefixLength,:50%,(suffixLength:50%
generatedParts.size()).toArray(new→String[prefixLength]);\n:100%
String[prefixLength]);\n→:100%
table.chooseSuffix(prefix);\n→:100%
joinSomeCorpusParts(generatedParts.toArray(new→String[generatedParts.size()]));\n:100%
String[generatedParts.size()]));\n→:100%
}\n\n\n→:100%
analyzeCorpus()→{\n:100%
we→want:50%,have:50%
want→to:100%
to→iterate:33%,the:33%,remove:33%
iterate→through:100%
through→all:100%
all→parts:100%
parts→of:100%
the→corpus,:50%,beginning,:50%
whether→they:100%
they→are:100%
are→letters:100%
letters→or:100%
or→characters\n:100%
characters\n→:100%
for→(int:100%
(int→index:100%
index→=:50%,<=:50%
0;→index:100%
<=→corpusParts.length:100%
corpusParts.length→-:100%
(suffixLength→+:100%
+→prefixLength);:33%,prefixLength;\n:33%,suffixLength;\n\n:33%
prefixLength);→index++):100%
index++)→{\n:100%
prefixStart→=:50%,+:50%
index;\n→:100%
prefixEnd→=:100%
suffixStart→=:50%,+:50%
prefixEnd;\n→:100%
suffixEnd→=:100%
suffixLength;\n\n→:100%
prefixParts→=:100%
prefixStart,→prefixEnd);\n:100%
prefixEnd);\n→:100%
suffixParts→=:100%
suffixStart,→suffixEnd);\n\n:100%
suffixEnd);\n\n→:100%
table.add(prefixParts,→suffixParts);\n:100%
suffixParts);\n→:100%
splitText(String→text):100%
text)→{\n:100%
splitText→=:100%
text.split(partSeparator);\n\n→:100%
when→splitting:100%
splitting→on:100%
on→"",:100%
"",→will:100%
will→add:100%
add→an:100%
an→extra:100%
extra→"":100%
""→to:100%
beginning,→so:100%
so→we:100%
have→to:100%
remove→that\n:100%
that\n→:100%
if→(partSeparator.equals("")):100%
(partSeparator.equals(""))→{\n:100%
Arrays.copyOfRange(splitText,→1,:100%
splitText.length);\n→:100%
splitText;\n→:100%
joinSomeCorpusParts(String[]→someParts:100%
someParts→):100%
)→{\n:100%
join(partSeparator,→someParts);\n:100%
someParts);\n→:100%

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static Utils.Preconditions.checkNotNull;
import static Utils.Preconditions.checkNotNull;
import static Utils.Preconditions.checkNotNull;
import static Utils.Preconditions.checkNotNull;
import static Utils.Strings.join;


public class WordGen {
      System.out.println("\n\nNow trying with default arguments\n");
      System.out.println("\n\nNow trying with default arguments\n");
        private String[] prefix = splitText(checkNotNull(corpus));
     this(corpus, null, null);
         return splitText;
     generatedParts.size() < maxParts) {
    this.maxParts = new WordGen(corpus_, startingString, maxParts, 1, 1, 1, 1, 1, " ");
      break;
     int suffixLength;
                      int prefixLength;
       private String corpus_ = new Table(partSeparator);
  String[] prefix = maxParts = inputs[1];
           startingString = Integer.parseInt(inputs[2]);
  this.corpusParts = Arrays.copyOfRange(corpusParts, 0, prefixLength);

    this(corpus, null, null);
                    }

    }
      this(corpus, null, null);
       return Arrays.copyOfRange(splitText, 1, " ");
    }
              }


          table.add(prefixParts, suffixParts);
             int prefixStart = new WordGen(corpus_, startingString, Integer maxParts) {
                                     Integer maxParts;
                                   if (partSeparator.equals("")) {
        int prefixLength;
  return Arrays.copyOfRange(splitText, 1, 1, 1, 1, " ");
    }


                  private String[] corpusParts;

   String[] prefix = inputs[1];
  private String partSeparator) {
  }
      }

       ArrayList<String> generatedParts = Integer.parseInt(inputs[2]);
      private int prefixLength, int suffixLength;
    }
             analyzeCorpus();
   private String[] corpusParts;

       if (partSeparator.equals("")) {
      System.out.print(w.getTableText());
  }

         newParts = Arrays.copyOfRange(corpusParts, 0, prefixLength);

   maxParts = 0; index = generatedParts.subList(generatedParts.size() - prefixLength, int prefixStart = new WordGen(corpus_, startingString, maxParts, 1, 1, "");
           WordGen {
   System.out.print(w.generateText());

   this.prefixLength = maxParts != null ? maxParts = new WordGen(corpus_, startingString, Integer maxParts;
            String corpus_ = startingString;
                  analyzeCorpus();
    }

  startingString = table.chooseSuffix(prefix);
         return Arrays.copyOfRange(splitText, 1, "");
                   String startingString, maxParts, 2, 1, " ");
      private String startingString, maxParts, 1, " ");
    String[] newParts = new WordGen(corpus_, startingString, maxParts, 1, "");
      this.prefixLength = index;
              return table.toString();
           }
     Integer maxParts;
            private Table table;
                  // we want to iterate through all parts of characters\n");
  String[] prefixParts = startingString;
    String[] newParts = checkNotNull(partSeparator);
        w = generatedParts.subList(generatedParts.size() - prefixLength, int suffixLength, String generateText() {
                 public WordGen(String corpus, String getTableText() {
      System.out.println();
        case 2:
          private Table table;
         String startingString = table.chooseSuffix(prefix);
        w = new Table(partSeparator);
  WordGen {
       System.out.println("\n\nNow trying with default arguments\n");
                        public String startingString = null;
       String partSeparator;


  WordGen {
            this.prefixLength = suffixStart = Integer.parseInt(inputs[2]);
  private String partSeparator) {
  case 2:
    System.out.println();
   public WordGen(String corpus, String startingString, maxParts, 2, 1, 1, " ");
      for (int index = null;
      System.out.print(w.getTableText());
         int prefixLength, int prefixStart = new Table(partSeparator);
 
```
### Abbot and Costello

```
$ java WordGen resources/whosonfirst.txt fly 20
Generating with default arguments

Ab→b:100%
bb→o:100%
bo→t:96%,d:2%,u:2%
ot→t:88%, :9%,h:2%,.:1%
tt→::87%,a:5%, :3%,i:2%,e:2%,,:1%
t:→ :100%
: →W:23%,T:19%,I:14%,N:12%,Y:10%,A:5%,S:3%,L:3%,H:3%,O:3%,G:2%,C:1%,D:1%,E:1%,F:1%,B:1%
 W→h:85%,e:13%,o:2%
We→l:100%
el→l:94%,d:6%
ll→o:62%, :25%,y:7%,,:3%,a:1%,i:1%,.:1%,e:1%
l →t:29%,m:16%,y:11%,I:11%,a:11%,r:5%,p:3%,h:3%,g:3%,k:3%,b:3%,w:3%,C:3%
 C→o:67%,e:33%
Co→s:100%
os→t:99%,e:1%
st→e:73%, :13%,.:5%,?:2%,!:2%,,:2%,a:2%,o:1%
te→l:91%,a:4%,r:3%,n:1%, :1%
lo→::91%,w:3%, :3%,n:2%,,:1%
o,→ :100%
, →I:14%,w:14%,y:11%,b:9%,W:9%,a:7%,i:5%,t:5%,h:5%,T:5%,g:2%,e:2%,l:2%,d:2%,Y:2%,p:2%,m:2%,s:2%
 I→ :64%,':33%,f:2%,s:2%
I'→m:70%,l:15%,d:10%,v:5%
'm→ :100%
m →n:31%,t:25%,a:19%,g:12%,o:6%,I:6%
 g→o:40%,u:30%,e:20%,i:8%,a:3%
go→t:44%,n:19%, :19%,i:6%,,:6%,o:6%
oi→n:100%
in→g:71%,d:10%,l:7%,.:2%,!:2%,s:2%,f:2%, :2%
ng→ :78%,.:8%,e:6%,i:3%,?:3%,,:3%
g →y:21%,t:18%,f:14%,a:14%,o:11%,w:4%,Y:4%,n:4%,W:4%,l:4%,i:4%
 t→h:61%,o:23%,e:10%,i:3%,r:1%,a:1%
to→ :82%,o:6%,m:6%,d:3%,p:3%
o →f:10%,y:8%,t:8%,T:8%,W:8%,i:6%,d:6%,o:6%,k:6%,I:6%,w:6%,N:6%,s:4%,g:4%,a:4%,h:2%,m:2%,p:2%,B:2%
 N→o:56%,a:41%,e:4%
Ne→w:100%
ew→ :100%
w →t:31%,w:18%,i:15%,d:8%,a:5%,I:5%,h:5%,y:5%,Y:3%,l:3%,B:3%
 Y→o:52%,e:35%,O:9%,a:4%
Yo→u:92%,r:8%
or→r:67%, :11%,k:11%,t:11%
rk→ :100%
k →y:20%,u:20%,m:20%,w:10%,A:10%,o:10%,t:10%
 w→h:67%,a:15%,i:9%,e:7%,r:2%
wi→t:50%,f:50%
it→.:32%, :26%,c:21%,h:6%,t:6%,?:6%,s:3%
th→e:60%,i:16%,r:15%,a:4%,o:2%, :2%,,:1%
h →t:40%,f:20%,c:20%,y:20%
 y→o:97%,a:3%
yo→u:100%
ou→ :56%,t:13%,':10%,.:3%,l:3%,g:3%,r:3%,,:2%,s:2%,n:2%,p:2%,!:2%
u.→ :50%,\n:50%
. →W:55%,S:15%,N:10%,s:5%,Y:5%,I:5%,A:5%
u →t:15%,g:15%,k:12%,d:12%,w:12%,m:9%,s:6%,a:6%,j:3%,i:3%,n:3%,h:3%,p:3%
 k→n:100%
kn→o:100%
no→w:71%,t:26%,b:3%
ow→ :62%,.:13%,':8%,s:7%,!:3%,-:2%,?:2%,n:2%,,:2%
 B→e:67%,o:33%
Bo→o:100%
oo→k:58%,f:17%,?:8%,.:8%,d:8%
ok→,:71%, :14%,i:14%
ki→n:88%,e:12%
ie→l:88%, :12%
e →b:16%,t:14%,g:12%,f:7%,a:7%,p:6%,o:6%,m:5%,n:4%,c:4%,w:4%,s:4%,i:3%,y:2%,h:2%,l:2%,H:1%,D:1%,Y:1%,I:1%,d:1%
 H→e:57%,i:14%,a:14%,o:14%
Ha→r:100%
ar→ :22%,n:22%,.:11%,o:11%,e:11%,m:11%,r:11%
rr→o:86%,i:14%
ri→g:71%,s:14%,p:14%
is→ :86%,t:9%,,:5%
s,→ :100%
he→ :65%,r:15%,n:8%,a:5%,i:3%,y:2%,s:1%,':1%
Ya→n:100%
an→d:32%,t:16%,.:11%, :8%,g:8%,a:5%,':5%,k:3%,?:3%,n:3%,,:3%,c:3%
nk→e:100%
ke→ :67%,e:33%
ee→':33%,m:33%,,:33%
e'→s:83%,r:17%
's→ :100%
s →o:24%,t:14%,n:14%,p:8%,i:8%,w:8%,h:3%,a:3%,d:3%,u:3%,c:2%,y:2%,r:2%,g:2%,l:1%,b:1%,v:1%,s:1%,m:1%,Y:1%
 m→e:67%,a:12%,o:12%,u:4%,y:4%
ma→n:100%
na→m:79%, :14%,g:7%
ag→e:50%,s:25%,a:25%
ge→t:73%, :13%,r:13%
er→ :24%,':15%,y:12%,t:9%,::9%,s:9%,e:6%,,:6%,?:6%,f:3%
r,→ :100%
ga→v:50%,i:50%
av→e:67%,y:33%
ve→ :59%,r:35%,n:6%
me→ :31%,s:18%,?:16%,.:14%,a:4%,t:4%,n:4%,,:4%,!:2%,b:2%
 a→s:27%,n:24%, :20%,l:6%,r:6%,t:4%,h:4%,b:4%,g:2%,m:2%
a →t:16%,c:16%,g:11%,d:11%,j:5%,o:5%,p:5%,a:5%,b:5%,h:5%,f:5%,l:5%,k:5%
 j→u:75%,o:25%
jo→b:100%
ob→ :50%,o:50%
b →a:100%
as→e:58%,k:22%, :17%,y:3%
 c→o:44%,a:31%,h:12%,e:12%
co→n:65%,a:15%,u:10%,m:5%,l:5%
oa→c:100%
ac→h:60%,k:40%
ch→e:37%,i:26%, :16%,a:11%,?:5%,,:5%
 f→i:83%,e:7%,o:2%,u:2%,a:2%,l:2%
fo→r:100%
r →n:19%,a:12%,o:12%,g:12%,F:6%,m:6%,t:6%,D:6%,b:6%,i:6%,s:6%
 l→e:62%,o:25%,i:12%
on→ :51%,':21%,d:15%,e:4%,n:3%,g:3%,t:1%,l:1%
u'→r:50%,v:33%,l:17%
'r→e:100%
re→ :54%,n:23%,.:15%,a:8%
 o→n:79%,u:12%,f:9%
n →t:28%,f:28%,s:20%,y:3%,h:3%,I:2%,e:2%,a:2%,u:2%,w:2%,p:2%,l:2%,g:2%,m:2%,k:2%
ea→m:31%,n:31%,d:12%,v:12%,k:6%,s:6%
am→e:80%,.:10%, :7%,?:3%
m.→\n:100%
.\n→\n:100%
\n\n→C:47%,A:47%,P:6%
\nC→o:100%
o:→ :100%
 L→o:100%
Lo→o:100%
 A→l:50%,n:30%,r:10%,b:10%
t,→ :100%
 i→t:54%,s:34%,n:7%,f:5%
if→ :40%,e:40%,f:20%
f →t:25%,i:25%,I:25%,y:12%,d:12%
h,→ :100%
mu→s:100%
us→t:57%,e:29%,i:14%
t →t:20%,b:13%,i:11%,k:8%,I:6%,a:6%,f:5%,g:4%,K:4%,s:3%,o:3%,w:3%,&:3%,c:2%,e:2%,d:1%,h:1%,y:1%,n:1%,p:1%
al→l:94%,k:6%
 p→l:46%,i:36%,u:7%,e:7%,a:4%
pl→a:87%,e:13%
la→y:80%,r:13%,t:7%
ay→i:38%, :25%,e:12%,s:8%,.:8%,?:4%,,:4%
ye→r:100%
rs→t:90%, :7%,.:3%
s.→\n:75%,.:17%, :8%
\nA→b:100%
I →d:23%,s:15%,t:13%,D:10%,w:10%,g:8%,p:8%,j:5%,m:5%,c:3%
ce→r:50%,n:50%
rt→a:75%,s:25%
ta→ :42%,i:25%,l:17%,y:17%
ai→n:50%,d:50%
nl→y:100%
ly→.:75%, :19%,?:6%
y →o:19%,d:12%,D:9%,t:9%,p:6%,g:6%,h:6%,m:3%,n:3%,a:3%,y:3%,i:3%,w:3%,c:3%,W:3%,r:3%,b:3%
 d→o:79%,i:9%,a:9%,r:3%
do→n:58%,e:12%, :12%,.:8%,l:8%,w:4%
o.→\n:69%, :31%
'v→e:100%
 n→a:65%,o:32%,e:3%
ne→y:33%,d:33%, :17%,v:17%
ev→e:100%
et→ :33%,s:33%,h:20%,i:7%,':7%
gu→y:100%
uy→ :58%,':25%,.:8%,s:8%
ys→ :67%,.:33%
 S→o:50%,u:20%,a:20%,t:10%
So→ :80%,m:20%
'l→l:100%
 h→i:33%,e:33%,a:19%,o:14%
ha→t:88%,v:6%,n:4%,s:2%
ei→r:75%,n:25%
ir→s:59%,d:35%, :7%
es→.:43%,,:19%, :19%,?:10%,e:5%,!:5%
nd→ :59%,.:31%,,:6%,?:3%
d →t:25%,b:22%,a:12%,I:12%,o:8%,c:5%,y:5%,h:5%,d:3%,r:3%
en→ :53%,t:27%,c:13%,.:7%
wh→o:68%,a:29%,e:3%
ho→':38%, :18%,.:16%,?:11%,u:5%,w:5%,s:2%,e:2%,r:2%
o'→s:100%
yi→n:100%
 O→h:60%,n:20%,k:20%
Oh→,:100%
 b→a:81%,u:8%,e:6%,r:4%
bu→n:50%,d:25%,t:25%
ut→ :55%,t:18%,.:9%,f:9%,!:9%
 s→e:45%,a:24%,i:12%,h:6%,t:6%,o:6%
se→c:32%,.:15%,m:12%,?:12%, :10%,!:10%,e:5%,,:3%
em→a:83%,s:17%
ms→ :100%
ey→ :50%,?:25%,.:25%
gi→v:75%,n:25%
iv→e:100%
ba→s:54%,l:38%,c:5%,g:3%
w-→a:100%
-a→-:100%
a-→d:100%
-d→a:100%
da→y:71%,r:29%
 v→e:100%
ry→ :67%,i:33%
pe→c:50%,t:50%
ec→o:76%,a:12%,t:6%,u:6%
cu→l:100%
ul→d:67%,i:33%
li→a:25%,k:25%,n:25%,s:25%
ia→r:100%
fu→n:100%
un→t:40%,d:20%,n:20%,s:20%
nn→a:80%,y:20%
ny→ :100%
s?→\n:100%
?\n→\n:100%
St→r:100%
tr→y:67%,a:33%
ra→l:92%,n:8%
..→.:50%,\n:40%,l:5%, :5%
.l→i:100%
ik→e:100%
 D→o:40%,e:30%,a:20%,i:10%
Di→z:100%
iz→z:100%
zz→y:100%
zy→ :100%
De→a:100%
n.→.:43%,\n:29%, :29%
Hi→s:100%
br→o:50%,e:50%
ro→w:83%,n:4%,u:4%,t:4%,p:4%
Da→f:100%
af→f:100%
ff→y:40%, :40%,e:20%
fy→.:50%, :50%
y.→\n:90%,.:5%, :5%
An→d:67%,o:33%
 F→r:100%
Fr→e:100%
nc→h:67%,y:33%
si→g:67%,n:17%,s:17%
h?→\n:100%
 G→o:100%
Go→o:67%,t:33%
of→ :43%,f:29%,y:29%
l,→ :100%
le→f:50%, :25%,t:12%,c:12%
t'→s:100%
e,→ :100%
we→ :67%,':33%
gs→,:100%
Wh→o:52%,a:35%,y:10%,e:4%
fi→r:73%,e:19%,n:8%
at→':42%, :26%,u:17%,c:8%,?:5%,e:2%,.:2%
d,→ :100%
Do→n:100%
n'→t:90%,s:10%
't→ :95%,,:5%
 K→n:100%
Kn→o:100%
hi→r:52%,n:23%,s:13%,t:10%,m:3%
rd→ :62%,.:25%,,:6%,?:6%
d.→\n:89%,.:5%, :5%
 T→h:73%,o:22%,a:3%,r:3%
Th→a:50%,e:40%,i:10%
wa→n:100%
nt→ :46%,i:15%,s:15%,e:8%,h:8%,.:8%
t.→\n:85%,.:8%, :8%
sa→y:50%,i:50%
w'→s:100%
Ar→e:100%
r?→\n:100%
Ye→s:100%
be→ :33%,h:33%,i:33%
o?→\n:100%
fe→l:50%, :17%,?:17%,r:17%
ws→ :75%,':25%
s'→ :100%
' →n:100%
sh→o:100%
ld→.:33%,?:22%,e:22%, :11%,!:11%
t?→\n:100%
e.→\n:87%, :13%
g.→\n:67%,.:33%
t!→\n:75%, :25%
!\n→\n:100%
sk→i:62%, :38%
YO→U:100%
OU→ :50%,!:50%
U →w:100%
e?→\n:100%
ah→e:100%
ad→ :50%,.:50%
\nP→A:100%
PA→U:100%
AU→S:100%
US→E:100%
SE→\n:100%
E\n→\n:100%
k,→ :100%
n?→\n:100%
Ce→r:100%
 r→i:83%,u:17%
ig→h:56%,n:44%
gh→t:100%
ht→.:57%, :29%,,:14%
pa→y:100%
 e→v:75%,a:25%
mo→r:67%,n:33%
ts→ :80%,.:10%,t:10%
y?→\n:75%, :25%
 E→v:100%
Ev→e:100%
ol→l:100%
Al→l:100%
He→':75%, :25%
oe→s:75%,v:25%
r.→ :100%
om→o:60%,e:40%
ti→m:56%,o:22%,n:22%
im→e:83%,.:17%
wn→ :100%
ct→s:100%
wr→o:100%
gn→ :50%,.:25%,s:25%
 u→p:100%
up→ :71%,l:14%,.:14%
p →t:80%,a:20%
n,→ :100%
Ho→w:100%
ns→ :67%,i:33%
y'→s:100%
No→w:60%,.:27%,,:7%, :7%
On→e:100%
e!→\n:100%
od→a:50%,y:33%, :17%
dy→!:33%,.:33%,':33%
y!→\n:100%
Ta→k:100%
ak→e:50%, :50%
sy→,:100%
y,→ :100%
ud→d:100%
dd→y:100%
u,→ :100%
Ok→.:100%
k.→\n:100%
w.→\n:88%, :12%
lk→i:100%
ab→o:100%
di→d:67%,f:33%
id→ :67%,.:17%,!:17%
hy→ :40%,.:40%,?:20%
io→n:100%
ed→ :100%
If→ :100%
d?→\n:100%
ck→ :80%,s:20%
n!→\n:100%
Wo→u:100%
ju→s:100%
w?→\n:100%
pu→t:100%
 &→ :100%
& →C:100%
To→m:44%,g:33%,d:22%
og→e:100%
r:→ :67%,T:33%
:T→h:100%
tf→i:100%
Su→r:100%
ur→a:73%, :13%,e:13%
ef→t:100%
ft→ :100%
de→r:100%
r'→s:100%
ug→h:100%
'd→ :100%
ya→.:100%
a.→\n:100%
nf→i:100%
d!→ :50%,\n:50%
! →I:60%,S:20%,H:20%
Be→c:100%
ca→t:71%,u:29%
au→s:100%
rf→i:100%
pi→t:70%,c:30%
tc→h:100%
m?→\n:100%
w!→\n:50%, :50%
g?→\n:100%
rm→,:100%
m,→ :100%
eh→i:100%
so→m:100%
fa→n:100%
cy→ :100%
g,→ :100%
my→ :100%
vy→ :100%
p.→ :100%
l.→ :100%
hr→o:100%
ic→k:100%
Is→ :100%
s!→\n:100%
Na→t:100%
tu→r:100%
eb→o:100%
Sa→m:100%
u!→ :100%
U!→ :100%
dr→o:100%
op→s:50%,.:50%
ps→ :100%
ru→n:100%
ks→ :100%
w,→ :100%
Tr→i:100%
ip→l:100%
fl→y:100%
? →I:100%
rn→!:100%

fly.

Abbott: I guy 

Now trying with words instead of characters

Abbott:→Well:100%
Well→Costello,:20%,you:20%,I:20%,then:20%,go:20%
Costello,→I'm:100%
I'm→not:29%,trying:14%,asking:7%,going:7%,only:7%,not...:7%,telling:7%,a:7%,gonna:7%,talking:7%
going→to:100%
to→find:11%,tell:7%,know:7%,who?\n\nAbbott::7%,first:7%,Naturally.\n\nAbbott::7%,Who.\n\nCostello::7%,New:4%,do.\n\nCostello::4%,throw:4%,know?\n\nCostello::4%,me:4%,do:4%,Who?\n\nCostello::4%,who.:4%,second.:4%,What.:4%,I:4%,Tomorrow,:4%,Because.:4%
New→York:100%
York→with:100%
with→you.:50%,that?\n\nCostello::50%
you.→You:100%
You→know:20%,don't:20%,throw:20%,mean:10%,gotta:10%,gonna:10%,ask:10%
know→what's:18%,all:9%,I've:9%,who's:9%,it:9%,the:9%,is:9%,Bookie:9%,I'm:9%,what:9%
Bookie→Harris,:100%
Harris,→the:100%
the→ball:25%,first:7%,guy's:7%,guy:7%,fellow's:5%,team.\n\nAbbott::2%,bags,:2%,manager?\n\nAbbott::2%,coach:2%,fellows':2%,guys.:2%,man's:2%,team.\n\nCostello::2%,money?\n\nAbbott::2%,money...\n\nAbbott::2%,coach,:2%,players:2%,players.\n\nAbbott::2%,third:2%,infield!:2%,pitcher's:2%,team.\n\nPAUSE\n\nCostello::2%,plate:2%,heavy:2%,ball.:2%,ball,:2%,Yankee's:2%
Yankee's→manager,:100%
manager,→gave:100%
gave→me:100%
me→who's:33%,their:17%,they:17%,a:17%,today?\n\nAbbott::17%
a→darn!\n\nAbbott::20%,time!\n\nAbbott::10%,catcher?\n\nAbbott::10%,couple:10%,catcher:10%,heavy:10%,good:10%,long:10%,job:10%
job→as:100%
as→coach:20%,long:20%,you're:20%,you!:20%,YOU!:20%
coach→for:50%,too?\n\nAbbott::50%
for→as:100%
long→as:50%,fly:50%
you're→on:50%,the:50%
on→second.\n\nCostello::11%,third:11%,first:9%,the:9%,second.\n\nAbbott::7%,first.\n\nCostello::7%,first.\n\nAbbott::4%,first!\n\nCostello::4%,second,:4%,third.\n\nCostello::4%,first?\n\nAbbott::4%,first,:4%,third...\n\nCostello::2%,third,:2%,second:2%,putting:2%,third.\n\nAbbott::2%,second?\n\nAbbott::2%,this:2%,first!:2%,my:2%
team.\n\nCostello:→Look:100%
Look→Abbott,:100%
Abbott,→if:100%
if→you're:50%,I:50%
coach,→you:100%
you→who's:12%,know:8%,gotta:8%,the:4%,don't:4%,their:4%,pay:4%,sign:4%,must:4%,mentioned:4%,just:4%,want:4%,insist:4%,now.\n\nCostello::4%,gonna:4%,say:4%,have:4%,don't,:4%,throw:4%,ask:4%
must→know:100%
all→the:33%,I:33%,you:33%
players.\n\nAbbott:→I:100%
I→don't:23%,throw:13%,Don't:10%,want:8%,get:5%,just:5%,pick:5%,say:5%,should.\n\nCostello::3%,mentioned:3%,go,:3%,putting:3%,mean:3%,wanna:3%,certainly:3%,said.\n\nAbbott::3%,said!\n\nAbbott::3%,said:3%
certainly→do.\n\nCostello::100%
do.\n\nCostello:→Well:50%,Is:50%
I've→never:100%
never→met:100%
met→the:100%
guys.→So:100%
So→I:50%,they:25%,you'll:25%
you'll→have:100%
have→to:67%,on:33%
tell→me:50%,you:12%,me.\n\nAbbott::12%,ya.\n\nCostello::12%,me.\n\nCostello::12%
their→names,:67%,French:33%
names,→and:33%,but:33%,pet:33%
and→I:17%,tell:8%,collects:8%,don't:8%,tomorrow's:8%,a:8%,throw:8%,then:8%,the:8%,throws:8%,hits:8%
then→I'll:50%,who's:50%
I'll→know:33%,tell:33%,break:33%
who's→on:46%,playing:23%,name?\n\nAbbott::8%,the:8%,pitching?\n\nAbbott::8%,got:8%
playing→first.\n\nCostello::29%,first?\n\nAbbott::14%,third?\n\nAbbott::14%,on:14%,third:14%,left:14%
team.\n\nAbbott:→Oh,:100%
Oh,→I'll:33%,he's:33%,that's:33%
but→you:100%
it→to:56%,easy,:11%,seems:11%,is:11%,back:11%
seems→to:100%
they→give:50%,tell:50%
give→a:67%,these:33%
these→ball:100%
ball→to:62%,and:31%,players:8%
players→now-a-days:50%,around.\n\nCostello::50%
now-a-days→very:100%
very→peculiar:100%
peculiar→names.\n\nCostello::100%
names.\n\nCostello:→You:100%
mean→funny:50%,the:50%
funny→names?\n\nAbbott::100%
names?\n\nAbbott:→Strange:50%,Well:50%
Strange→names,:100%
pet→names...like:100%
names...like→Dizzy:100%
Dizzy→Dean...\n\nCostello::100%
Dean...\n\nCostello:→His:50%,And:50%
His→brother:100%
brother→Daffy.\n\nAbbott::100%
Daffy.\n\nAbbott:→Daffy:100%
Daffy→Dean...\n\nCostello::100%
And→their:50%,you:50%
French→cousin.\n\nAbbott::100%
cousin.\n\nAbbott:→French?\n\nCostello::100%
French?\n\nCostello:→Goofy.\n\nAbbott::100%
Goofy.\n\nAbbott:→Goofy:100%
Goofy→Dean.:100%
Dean.→Well,:100%
Well,→let's:33%,don't:33%,I:33%
let's→see,:100%
see,→we:100%
we→have:50%,got:50%
bags,→Who's:100%
Who's→on:62%,playing:38%
first,→What's:100%
What's→on:71%,wrong:14%,the:14%
second,→I:100%
Don't→Know:50%,Know's:25%,Know.:25%
Know→is:50%,throws:50%
is→on:50%,the:7%,when:7%,what's:7%,playing:7%,not:7%,a:7%,drops:7%
third...\n\nCostello:→That's:100%
That's→it.\n\nCostello::27%,what:20%,right.\n\nCostello::13%,the:7%,who?\n\nAbbott::7%,who's:7%,how:7%,all:7%,different.\n\nCostello::7%
what→I:60%,do:20%,I'm:20%
want→to:83%,who:17%
find→out:67%,out.\n\nAbbott::33%
out.\n\nAbbott:→I:100%
say→Who's:33%,is:33%,who's:33%
Know's→on:100%
third.\n\nCostello:→Are:50%,There:50%
Are→you:100%
manager?\n\nAbbott:→Yes.\n\nCostello::100%
Yes.\n\nCostello:→You:25%,And:25%,I:25%,Well:25%
gonna→be:33%,tell:33%,throw:33%
be→the:100%
too?\n\nAbbott:→Yes.\n\nCostello::100%
don't→know.\n\nAbbott:21%,know.\n\nAbbott::14%,want:14%,give:14%,change:7%,know:7%,even:7%,know!:7%,go:7%
fellows'→names?\n\nAbbott::100%
should.\n\nCostello:→Well:100%
first?\n\nAbbott:→Yes.\n\nCostello::33%,That's:33%,What's:33%
fellow's→name.\n\nAbbott::50%,name:50%
name.\n\nAbbott:→Who.\n\nCostello::100%
Who.\n\nCostello:→The:62%,Naturally.\n\nAbbott::25%,How:12%
The→guy:33%,left:22%,guy.\n\nAbbott::11%,first:11%,pitcher's:11%,catcher's:11%
guy→on:29%,playing...\n\nAbbott::14%,that:14%,out:14%,runs:14%,gets:14%
first.\n\nAbbott:→Who.\n\nCostello::50%,That's:50%
first→base.\n\nAbbott::25%,base?\n\nAbbott::17%,baseman:8%,baseman.\n\nAbbott::8%,baseman,:8%,baseman?\n\nAbbott::8%,base.:8%,thing:8%,base,:8%
baseman.\n\nAbbott:→Who.\n\nCostello::100%
playing...\n\nAbbott:→Who:100%
Who→is:50%,gets:17%,on:17%,picks:17%
first!\n\nCostello:→I'm:50%,I:50%
asking→you:60%,YOU:20%,you,:20%
YOU→who's:100%
man's→name.\n\nCostello::100%
name.\n\nCostello:→That's:50%,If:50%
name?\n\nAbbott:→Why.\n\nCostello::29%,Who.\n\nCostello::14%,Yes.\n\nCostello::14%,Tomorrow.\n\nCostello::14%,What's:14%,Today.\n\nCostello::14%
go→ahead:33%,off:33%,ahead.\n\nAbbott::33%
ahead→and:100%
me.\n\nAbbott:→That's:50%,You:50%
it.\n\nCostello:→That's:25%,All:25%,Who:12%,Whose:12%,Who?\n\nAbbott::12%,Same:12%
who?\n\nAbbott:→Yes.\n\nPAUSE\n\nCostello::33%,Now:33%,Naturally.\n\nCostello::33%
Yes.\n\nPAUSE\n\nCostello:→Look,:50%,All:50%
Look,→you:40%,all:20%,You:20%,if:20%
gotta→first:25%,outfield?\n\nAbbott::25%,pitcher:25%,get:25%
baseman?\n\nAbbott:→Certainly.\n\nCostello::100%
Certainly.\n\nCostello:→Who's:50%,The:50%
right.\n\nCostello:→When:33%,Ok.\n\nAbbott::33%,I:33%
When→you:50%,he:50%
pay→off:100%
off→the:50%,it.\n\nAbbott::50%
baseman→every:100%
every→month,:50%,dollar.:50%
month,→who:100%
who→gets:25%,did:25%,on:25%,has:25%
gets→the:50%,up.:25%,up:25%
money?\n\nAbbott:→Every:100%
Every→dollar:100%
dollar→of:100%
of→it.\n\nCostello::33%,the:33%,days:33%
All→I'm:40%,right.\n\nPAUSE\n\nCostello::20%,right,:20%,we:20%
trying→to:100%
out→is:50%,of:25%,at:25%
name→on:75%,in:25%
base.\n\nAbbott:→Who.\n\nCostello::33%,No.:33%,Yes!\n\nCostello::33%
that→gets...\n\nAbbott::100%
gets...\n\nAbbott:→That's:100%
money...\n\nAbbott:→He:100%
He→does,:100%
does,→every:100%
dollar.→Sometimes:100%
Sometimes→his:100%
his→wife:33%,name?\n\nAbbott::33%,name.\n\nCostello::33%
wife→comes:100%
comes→down:100%
down→and:100%
collects→it.\n\nCostello::100%
Whose→wife?\n\nAbbott::100%
wife?\n\nAbbott:→Yes.\n\nPAUSE\n\nAbbott::100%
Yes.\n\nPAUSE\n\nAbbott:→What's:100%
wrong→with:100%
that?\n\nCostello:→Look,:100%
wanna→know:100%
when→you:100%
sign→up:50%,his:50%
up→the:80%,and:20%
baseman,→how:100%
how→does:33%,he:33%,did:33%
does→he:100%
he→sign:25%,sign...\n\nAbbott::25%,signs:25%,bunts:25%
guy.\n\nAbbott:→Who.\n\nCostello::100%
How→does:100%
sign...\n\nAbbott:→That's:100%
signs→it.\n\nCostello::100%
Who?\n\nAbbott:→Yes.\n\nPAUSE\n\nCostello::50%,Naturally.\n\nCostello::50%
what's→the:100%
guy's→name:100%
No.→What:75%,Who's:25%
What→is:44%,time:22%,time?\n\nAbbott::11%,am:11%,throws:11%
second→base.\n\nCostello::100%
base.\n\nCostello:→I'm:100%
not→asking:43%,changing:14%,talking:14%,pitching.\n\nCostello::14%,saying:14%
second.\n\nAbbott:→Who's:100%
first.\n\nCostello:→I:40%,One:20%,What's:20%,I'm:20%
One→base:100%
base→at:50%,and:50%
at→a:50%,first:50%
time!\n\nAbbott:→Well,:100%
change→the:100%
around.\n\nCostello:→I'm:100%
changing→nobody!\n\nAbbott::100%
nobody!\n\nAbbott:→Take:100%
Take→it:100%
easy,→buddy.\n\nCostello::100%
buddy.\n\nCostello:→I'm:100%
only→asking:100%
you,→who's:100%
base?\n\nAbbott:→Why:50%,No.:25%,That's:25%
Ok.\n\nAbbott:→All:100%
right.\n\nPAUSE\n\nCostello:→What's:100%
second.\n\nCostello:→I'm:40%,I:40%,You:20%
know.\n\nAbbott:→He's:100%
He's→on:100%
third,→we're:100%
we're→not:100%
talking→about:50%,about!\n\nPAUSE\n\nAbbott::50%
about→him.\n\nCostello::100%
him.\n\nCostello:→Now:100%
Now→who's:22%,how:11%,listen.:11%,you've:11%,the:11%,that's:11%,who:11%,you:11%
did→I:100%
get→on:33%,behind:33%,it.:33%
third→base?\n\nAbbott::29%,baseman's:14%,again!\n\nPAUSE\n\nCostello::14%,base:14%,base?\n\nCostello::14%,and:14%
Why→you:50%,do:50%
mentioned→his:50%,the:50%
If→I:100%
baseman's→name,:100%
name,→who:100%
third?\n\nAbbott:→No.:100%
There→I:100%
go,→back:100%
back→on:50%,to:50%
again!\n\nPAUSE\n\nCostello:→Would:100%
Would→you:100%
just→thought:67%,stay:33%
stay→on:50%,out:50%
it.\n\nAbbott:→All:100%
right,→what:100%
do→you:67%,some:33%
know?\n\nCostello:→Now:100%
insist→on:100%
putting→Who:50%,on:50%
base?\n\nCostello:→What:100%
am→I:100%
third.\n\nAbbott:→No.:100%
second?\n\nAbbott:→Who:100%
know.\n\nAbbott→&:100%
&→Costello:100%
Costello→Together::67%,Together:Third:33%
Together:Third→base!\n\nPAUSE\n\nCostello::100%
base!\n\nPAUSE\n\nCostello:→Look,:33%,The:33%,Gotta:33%
outfield?\n\nAbbott:→Sure.\n\nCostello::100%
Sure.\n\nCostello:→The:100%
left→fielder's:50%,field.\n\nAbbott::25%,field?\n\nAbbott::25%
fielder's→name?\n\nAbbott::100%
Why.\n\nCostello:→I:50%,Because!\n\nAbbott::50%
thought→I'd:100%
I'd→ask:50%,tell:50%
ask→you.\n\nAbbott::33%,me.\n\nCostello::33%,me.\n\nAbbott::33%
you.\n\nAbbott:→Well,:100%
ya.\n\nCostello:→Then:100%
Then→tell:50%,go:50%
field.\n\nAbbott:→Who's:100%
not...→stay:100%
infield!→I:100%
in→left:100%
field?\n\nAbbott:→No,:100%
No,→What:100%
Together:→Third:100%
Third→base!\n\nPAUSE\n\nCostello::100%
Because!\n\nAbbott:→Oh,:100%
he's→centerfield.\n\nPAUSE\n\nCostello::100%
centerfield.\n\nPAUSE\n\nCostello:→Look,:100%
pitcher→on:100%
this→team?\n\nAbbott::100%
team?\n\nAbbott:→Sure.\n\nCostello::100%
pitcher's→name?\n\nAbbott::100%
Tomorrow.\n\nCostello:→You:100%
today?\n\nAbbott:→I'm:100%
telling→you:100%
now.\n\nCostello:→Then:100%
ahead.\n\nAbbott:→Tomorrow!\n\nCostello::100%
Tomorrow!\n\nCostello:→What:100%
time?\n\nAbbott:→What:100%
time→what?\n\nCostello::50%,tomorrow:50%
what?\n\nCostello:→What:100%
tomorrow→are:100%
are→you:100%
pitching?\n\nAbbott:→Now:100%
listen.→Who:100%
pitching.\n\nCostello:→I'll:100%
break→your:100%
your→arm,:100%
arm,→you:100%
first!→I:100%
Gotta→a:100%
catcher?\n\nAbbott:→Certainly.\n\nCostello::100%
catcher's→name?\n\nAbbott::100%
Today.\n\nCostello:→Today,:100%
Today,→and:100%
tomorrow's→pitching.\n\nAbbott::100%
pitching.\n\nAbbott:→Now:100%
you've→got:50%,said:50%
got→it.\n\nCostello::33%,is:33%,it?\n\nAbbott::33%
couple→of:100%
days→on:100%
team.\n\nPAUSE\n\nCostello:→You:100%
catcher→too.\n\nAbbott::100%
too.\n\nAbbott:→So:100%
me.\n\nCostello:→I:100%
behind→the:100%
plate→to:100%
some→fancy:100%
fancy→catching,:100%
catching,→Tomorrow's:100%
Tomorrow's→pitching:100%
pitching→on:100%
my→team:100%
team→and:100%
heavy→hitter:100%
hitter→gets:50%,bunts:50%
up.→Now:100%
bunts→the:100%
ball.→When:100%
ball,→me,:100%
me,→being:100%
being→a:100%
good→catcher,:100%
catcher,→I'm:100%
throw→the:73%,it:27%
base.→So:100%
pick→up:100%
that's→the:50%,our:50%
thing→you've:100%
said→right.\n\nCostello::50%,I:50%
even→know:100%
about!\n\nPAUSE\n\nAbbott:→That's:100%
Is→to:100%
Yes!\n\nCostello:→Now:100%
it?\n\nAbbott:→Naturally.\n\nPAUSE\n\nCostello::50%,Naturally.\n\nCostello::50%
Naturally.\n\nPAUSE\n\nCostello:→Look,:100%
base,→somebody's:100%
somebody's→gotta:100%
it.→Now:100%
has→it?\n\nAbbott::100%
Naturally.\n\nCostello:→Who?\n\nAbbott::25%,Naturally?\n\nAbbott::25%,So:25%,Now:25%
Naturally?\n\nAbbott:→Naturally.\n\nCostello::100%
Naturally.\n\nAbbott:→That's:60%,No:20%,You:20%
No→you:100%
don't,→you:100%
different.\n\nCostello:→That's:100%
said.\n\nAbbott:→You're:100%
You're→not:100%
saying→it...\n\nCostello::100%
it...\n\nCostello:→I:100%
said!\n\nAbbott:→You:100%
Who?\n\nCostello:→Naturally.\n\nAbbott::100%
Same→as:100%
you!→Same:100%
YOU!→I:100%
who.→Whoever:100%
Whoever→it:100%
drops→the:100%
runs→to:100%
second.→Who:100%
picks→up:100%
throws→it:100%
What.→What:100%
Know.→I:100%
Tomorrow,→Triple:100%
Triple→play.:100%
play.→Another:100%
Another→guy:100%
hits→a:100%
fly→ball:100%
Because.→Why?:100%
Why?→I:100%
know!→He's:100%
darn!\n\nAbbott:→What?\n\nCostello::50%,Oh,:50%
What?\n\nCostello:→I:100%
our→shortstop.:100%

fly ball to me who's on first base?

Abbott: No. What is on third base?

Costello: What is not asking you who's
```