package year2024.day13

val SAMPLE = """Button A: X+94, Y+34
Button B: X+22, Y+67
Prize: X=8400, Y=5400

Button A: X+26, Y+66
Button B: X+67, Y+21
Prize: X=12748, Y=12176

Button A: X+17, Y+86
Button B: X+84, Y+37
Prize: X=7870, Y=6450

Button A: X+69, Y+23
Button B: X+27, Y+71
Prize: X=18641, Y=10279"""

val INPUT = """Button A: X+57, Y+16
Button B: X+20, Y+74
Prize: X=3288, Y=1772

Button A: X+60, Y+17
Button B: X+18, Y+40
Prize: X=12452, Y=2246

Button A: X+53, Y+30
Button B: X+12, Y+48
Prize: X=10554, Y=7484

Button A: X+94, Y+41
Button B: X+16, Y+34
Prize: X=5960, Y=3140

Button A: X+51, Y+15
Button B: X+16, Y+53
Prize: X=15646, Y=8526

Button A: X+15, Y+44
Button B: X+55, Y+34
Prize: X=14665, Y=15766

Button A: X+61, Y+16
Button B: X+17, Y+39
Prize: X=15223, Y=18028

Button A: X+39, Y+24
Button B: X+13, Y+42
Prize: X=17748, Y=10592

Button A: X+61, Y+26
Button B: X+28, Y+69
Prize: X=15037, Y=4719

Button A: X+14, Y+59
Button B: X+55, Y+19
Prize: X=15659, Y=19538

Button A: X+58, Y+20
Button B: X+16, Y+37
Prize: X=6210, Y=4660

Button A: X+24, Y+46
Button B: X+49, Y+16
Prize: X=3921, Y=4284

Button A: X+83, Y+62
Button B: X+31, Y+88
Prize: X=9119, Y=12518

Button A: X+83, Y+44
Button B: X+31, Y+54
Prize: X=5857, Y=4570

Button A: X+46, Y+32
Button B: X+32, Y+96
Prize: X=5332, Y=6880

Button A: X+55, Y+28
Button B: X+30, Y+50
Prize: X=9175, Y=12224

Button A: X+62, Y+35
Button B: X+11, Y+34
Prize: X=19628, Y=9357

Button A: X+37, Y+55
Button B: X+50, Y+11
Prize: X=6401, Y=4829

Button A: X+45, Y+77
Button B: X+36, Y+12
Prize: X=9323, Y=14923

Button A: X+31, Y+89
Button B: X+92, Y+26
Prize: X=8931, Y=4209

Button A: X+24, Y+45
Button B: X+57, Y+32
Prize: X=10244, Y=13383

Button A: X+33, Y+23
Button B: X+34, Y+84
Prize: X=3431, Y=4321

Button A: X+54, Y+20
Button B: X+23, Y+47
Prize: X=13618, Y=11332

Button A: X+48, Y+21
Button B: X+18, Y+55
Prize: X=2684, Y=15337

Button A: X+72, Y+31
Button B: X+17, Y+30
Prize: X=6191, Y=3913

Button A: X+31, Y+53
Button B: X+58, Y+31
Prize: X=5857, Y=17657

Button A: X+37, Y+94
Button B: X+76, Y+58
Prize: X=6250, Y=8584

Button A: X+54, Y+18
Button B: X+27, Y+67
Prize: X=18890, Y=13858

Button A: X+47, Y+95
Button B: X+98, Y+33
Prize: X=4349, Y=5819

Button A: X+56, Y+28
Button B: X+27, Y+94
Prize: X=6057, Y=9066

Button A: X+21, Y+59
Button B: X+86, Y+32
Prize: X=2417, Y=2179

Button A: X+67, Y+96
Button B: X+55, Y+16
Prize: X=2626, Y=1376

Button A: X+72, Y+13
Button B: X+17, Y+78
Prize: X=15058, Y=14927

Button A: X+43, Y+39
Button B: X+16, Y+95
Prize: X=4648, Y=12023

Button A: X+36, Y+13
Button B: X+23, Y+36
Prize: X=9934, Y=8941

Button A: X+55, Y+14
Button B: X+18, Y+70
Prize: X=5711, Y=1306

Button A: X+85, Y+31
Button B: X+44, Y+73
Prize: X=9481, Y=6818

Button A: X+71, Y+37
Button B: X+15, Y+53
Prize: X=13491, Y=15729

Button A: X+21, Y+66
Button B: X+31, Y+11
Prize: X=2836, Y=1826

Button A: X+27, Y+53
Button B: X+60, Y+24
Prize: X=10451, Y=225

Button A: X+44, Y+31
Button B: X+13, Y+32
Prize: X=18546, Y=19534

Button A: X+16, Y+48
Button B: X+44, Y+38
Prize: X=1220, Y=2250

Button A: X+17, Y+57
Button B: X+78, Y+66
Prize: X=7040, Y=8940

Button A: X+13, Y+38
Button B: X+30, Y+19
Prize: X=4083, Y=9658

Button A: X+11, Y+29
Button B: X+78, Y+44
Prize: X=19500, Y=4346

Button A: X+14, Y+90
Button B: X+88, Y+16
Prize: X=4654, Y=4082

Button A: X+84, Y+19
Button B: X+18, Y+86
Prize: X=2748, Y=8159

Button A: X+17, Y+57
Button B: X+68, Y+13
Prize: X=992, Y=6337

Button A: X+72, Y+35
Button B: X+13, Y+47
Prize: X=7519, Y=3887

Button A: X+95, Y+11
Button B: X+19, Y+41
Prize: X=6042, Y=2756

Button A: X+21, Y+37
Button B: X+53, Y+31
Prize: X=10165, Y=5755

Button A: X+22, Y+52
Button B: X+79, Y+40
Prize: X=2887, Y=4036

Button A: X+17, Y+38
Button B: X+73, Y+42
Prize: X=14580, Y=15240

Button A: X+46, Y+84
Button B: X+67, Y+32
Prize: X=4317, Y=2372

Button A: X+37, Y+13
Button B: X+21, Y+55
Prize: X=3836, Y=2182

Button A: X+32, Y+95
Button B: X+65, Y+12
Prize: X=1190, Y=2447

Button A: X+67, Y+67
Button B: X+16, Y+73
Prize: X=4877, Y=7214

Button A: X+11, Y+57
Button B: X+77, Y+37
Prize: X=1782, Y=5252

Button A: X+77, Y+33
Button B: X+11, Y+59
Prize: X=2806, Y=9794

Button A: X+88, Y+37
Button B: X+40, Y+88
Prize: X=6344, Y=5657

Button A: X+45, Y+86
Button B: X+85, Y+49
Prize: X=9780, Y=10863

Button A: X+94, Y+54
Button B: X+21, Y+52
Prize: X=8715, Y=6564

Button A: X+22, Y+23
Button B: X+16, Y+70
Prize: X=3002, Y=6761

Button A: X+17, Y+65
Button B: X+54, Y+22
Prize: X=12400, Y=1072

Button A: X+46, Y+91
Button B: X+77, Y+16
Prize: X=3795, Y=4372

Button A: X+84, Y+21
Button B: X+32, Y+45
Prize: X=5148, Y=2286

Button A: X+38, Y+17
Button B: X+28, Y+61
Prize: X=3498, Y=18774

Button A: X+56, Y+93
Button B: X+82, Y+24
Prize: X=1422, Y=2025

Button A: X+69, Y+48
Button B: X+11, Y+99
Prize: X=3505, Y=9198

Button A: X+85, Y+30
Button B: X+52, Y+80
Prize: X=12113, Y=10070

Button A: X+17, Y+74
Button B: X+74, Y+13
Prize: X=1862, Y=16659

Button A: X+59, Y+83
Button B: X+52, Y+17
Prize: X=2259, Y=2841

Button A: X+12, Y+39
Button B: X+74, Y+31
Prize: X=8948, Y=7103

Button A: X+63, Y+15
Button B: X+11, Y+54
Prize: X=14625, Y=1670

Button A: X+43, Y+11
Button B: X+16, Y+29
Prize: X=3189, Y=2083

Button A: X+11, Y+50
Button B: X+68, Y+26
Prize: X=3360, Y=10800

Button A: X+36, Y+99
Button B: X+49, Y+23
Prize: X=2107, Y=5012

Button A: X+52, Y+36
Button B: X+11, Y+55
Prize: X=3816, Y=5864

Button A: X+29, Y+54
Button B: X+48, Y+18
Prize: X=3194, Y=3014

Button A: X+38, Y+16
Button B: X+23, Y+44
Prize: X=15107, Y=6748

Button A: X+21, Y+43
Button B: X+63, Y+31
Prize: X=4032, Y=3258

Button A: X+30, Y+73
Button B: X+77, Y+41
Prize: X=1935, Y=2513

Button A: X+31, Y+97
Button B: X+41, Y+24
Prize: X=4929, Y=8957

Button A: X+31, Y+52
Button B: X+92, Y+45
Prize: X=7914, Y=4092

Button A: X+41, Y+23
Button B: X+30, Y+59
Prize: X=594, Y=1645

Button A: X+79, Y+36
Button B: X+15, Y+74
Prize: X=6373, Y=7740

Button A: X+69, Y+14
Button B: X+19, Y+76
Prize: X=7888, Y=14756

Button A: X+41, Y+22
Button B: X+28, Y+51
Prize: X=3423, Y=8491

Button A: X+14, Y+32
Button B: X+94, Y+25
Prize: X=7496, Y=4793

Button A: X+14, Y+45
Button B: X+82, Y+52
Prize: X=2262, Y=15016

Button A: X+38, Y+39
Button B: X+53, Y+13
Prize: X=6695, Y=3601

Button A: X+48, Y+20
Button B: X+32, Y+61
Prize: X=4448, Y=16219

Button A: X+16, Y+38
Button B: X+70, Y+36
Prize: X=8560, Y=6000

Button A: X+80, Y+13
Button B: X+16, Y+75
Prize: X=7952, Y=3971

Button A: X+16, Y+43
Button B: X+30, Y+12
Prize: X=9258, Y=12615

Button A: X+61, Y+71
Button B: X+15, Y+86
Prize: X=4115, Y=9656

Button A: X+20, Y+41
Button B: X+56, Y+20
Prize: X=4480, Y=13060

Button A: X+42, Y+87
Button B: X+53, Y+36
Prize: X=4581, Y=4398

Button A: X+39, Y+17
Button B: X+45, Y+86
Prize: X=7437, Y=9150

Button A: X+91, Y+29
Button B: X+44, Y+80
Prize: X=11469, Y=9527

Button A: X+12, Y+65
Button B: X+78, Y+12
Prize: X=16640, Y=16274

Button A: X+19, Y+65
Button B: X+61, Y+19
Prize: X=12120, Y=756

Button A: X+98, Y+40
Button B: X+64, Y+98
Prize: X=10122, Y=6144

Button A: X+32, Y+89
Button B: X+38, Y+20
Prize: X=3456, Y=6870

Button A: X+60, Y+19
Button B: X+22, Y+58
Prize: X=1360, Y=10182

Button A: X+14, Y+83
Button B: X+92, Y+62
Prize: X=6170, Y=8057

Button A: X+88, Y+25
Button B: X+53, Y+82
Prize: X=8170, Y=3526

Button A: X+27, Y+73
Button B: X+63, Y+11
Prize: X=17603, Y=5163

Button A: X+53, Y+27
Button B: X+33, Y+62
Prize: X=7611, Y=3599

Button A: X+20, Y+47
Button B: X+54, Y+20
Prize: X=4468, Y=13566

Button A: X+71, Y+22
Button B: X+11, Y+52
Prize: X=10021, Y=372

Button A: X+91, Y+37
Button B: X+29, Y+78
Prize: X=9603, Y=7546

Button A: X+75, Y+18
Button B: X+33, Y+94
Prize: X=8388, Y=7264

Button A: X+21, Y+96
Button B: X+88, Y+39
Prize: X=1808, Y=4269

Button A: X+13, Y+39
Button B: X+48, Y+28
Prize: X=10721, Y=6695

Button A: X+55, Y+86
Button B: X+96, Y+39
Prize: X=9429, Y=7077

Button A: X+19, Y+50
Button B: X+75, Y+46
Prize: X=18244, Y=17920

Button A: X+52, Y+13
Button B: X+81, Y+81
Prize: X=4892, Y=3410

Button A: X+17, Y+95
Button B: X+54, Y+61
Prize: X=4990, Y=10791

Button A: X+28, Y+62
Button B: X+90, Y+21
Prize: X=7450, Y=5621

Button A: X+56, Y+19
Button B: X+16, Y+38
Prize: X=11832, Y=6295

Button A: X+11, Y+50
Button B: X+79, Y+43
Prize: X=19472, Y=18896

Button A: X+13, Y+21
Button B: X+58, Y+23
Prize: X=4216, Y=2781

Button A: X+49, Y+17
Button B: X+13, Y+28
Prize: X=3018, Y=2339

Button A: X+47, Y+98
Button B: X+19, Y+11
Prize: X=5321, Y=8319

Button A: X+45, Y+19
Button B: X+18, Y+33
Prize: X=11240, Y=12893

Button A: X+16, Y+60
Button B: X+70, Y+52
Prize: X=6558, Y=6700

Button A: X+59, Y+88
Button B: X+85, Y+31
Prize: X=12375, Y=9167

Button A: X+79, Y+27
Button B: X+47, Y+75
Prize: X=6890, Y=6834

Button A: X+14, Y+67
Button B: X+97, Y+38
Prize: X=2307, Y=3795

Button A: X+34, Y+75
Button B: X+69, Y+19
Prize: X=7833, Y=6489

Button A: X+16, Y+44
Button B: X+88, Y+35
Prize: X=8280, Y=5589

Button A: X+77, Y+45
Button B: X+18, Y+64
Prize: X=5887, Y=6061

Button A: X+17, Y+63
Button B: X+48, Y+11
Prize: X=15217, Y=13649

Button A: X+26, Y+81
Button B: X+66, Y+14
Prize: X=6356, Y=4990

Button A: X+16, Y+58
Button B: X+71, Y+19
Prize: X=6828, Y=4728

Button A: X+14, Y+49
Button B: X+75, Y+20
Prize: X=11655, Y=9305

Button A: X+41, Y+14
Button B: X+12, Y+59
Prize: X=2130, Y=16548

Button A: X+38, Y+56
Button B: X+34, Y+12
Prize: X=810, Y=16024

Button A: X+87, Y+79
Button B: X+11, Y+87
Prize: X=9691, Y=16347

Button A: X+73, Y+44
Button B: X+21, Y+47
Prize: X=3829, Y=2720

Button A: X+71, Y+18
Button B: X+68, Y+67
Prize: X=12998, Y=7923

Button A: X+13, Y+54
Button B: X+66, Y+14
Prize: X=9606, Y=2602

Button A: X+15, Y+86
Button B: X+91, Y+53
Prize: X=1744, Y=8124

Button A: X+22, Y+64
Button B: X+65, Y+28
Prize: X=6224, Y=16744

Button A: X+61, Y+45
Button B: X+21, Y+98
Prize: X=4214, Y=11277

Button A: X+28, Y+57
Button B: X+52, Y+31
Prize: X=584, Y=7698

Button A: X+11, Y+40
Button B: X+82, Y+16
Prize: X=4565, Y=4184

Button A: X+14, Y+64
Button B: X+26, Y+28
Prize: X=2332, Y=2756

Button A: X+19, Y+61
Button B: X+34, Y+26
Prize: X=704, Y=1096

Button A: X+50, Y+23
Button B: X+21, Y+49
Prize: X=16626, Y=17288

Button A: X+60, Y+13
Button B: X+22, Y+60
Prize: X=19160, Y=8499

Button A: X+53, Y+25
Button B: X+26, Y+52
Prize: X=5752, Y=9516

Button A: X+94, Y+28
Button B: X+34, Y+49
Prize: X=8400, Y=4368

Button A: X+36, Y+25
Button B: X+20, Y+59
Prize: X=3640, Y=3836

Button A: X+72, Y+46
Button B: X+21, Y+42
Prize: X=13244, Y=14486

Button A: X+16, Y+48
Button B: X+62, Y+12
Prize: X=9594, Y=5924

Button A: X+11, Y+92
Button B: X+73, Y+32
Prize: X=3404, Y=5328

Button A: X+17, Y+49
Button B: X+50, Y+21
Prize: X=5056, Y=2877

Button A: X+53, Y+11
Button B: X+11, Y+69
Prize: X=13648, Y=14976

Button A: X+61, Y+38
Button B: X+12, Y+33
Prize: X=4094, Y=3214

Button A: X+20, Y+76
Button B: X+49, Y+12
Prize: X=3667, Y=6444

Button A: X+12, Y+52
Button B: X+62, Y+28
Prize: X=1132, Y=6988

Button A: X+35, Y+15
Button B: X+32, Y+50
Prize: X=8002, Y=9430

Button A: X+11, Y+74
Button B: X+79, Y+50
Prize: X=2429, Y=6230

Button A: X+28, Y+54
Button B: X+46, Y+28
Prize: X=9642, Y=9706

Button A: X+30, Y+46
Button B: X+28, Y+12
Prize: X=11486, Y=4046

Button A: X+93, Y+51
Button B: X+11, Y+36
Prize: X=5827, Y=3615

Button A: X+20, Y+40
Button B: X+56, Y+36
Prize: X=2696, Y=3036

Button A: X+13, Y+56
Button B: X+77, Y+22
Prize: X=11704, Y=14328

Button A: X+51, Y+14
Button B: X+30, Y+68
Prize: X=4463, Y=17990

Button A: X+12, Y+90
Button B: X+45, Y+16
Prize: X=1836, Y=3482

Button A: X+82, Y+16
Button B: X+11, Y+70
Prize: X=8687, Y=14862

Button A: X+46, Y+24
Button B: X+42, Y+80
Prize: X=2984, Y=4984

Button A: X+14, Y+31
Button B: X+42, Y+27
Prize: X=12688, Y=11310

Button A: X+72, Y+19
Button B: X+59, Y+94
Prize: X=2718, Y=2129

Button A: X+13, Y+67
Button B: X+71, Y+19
Prize: X=15897, Y=11843

Button A: X+91, Y+41
Button B: X+47, Y+75
Prize: X=6855, Y=4165

Button A: X+23, Y+52
Button B: X+97, Y+45
Prize: X=7376, Y=5695

Button A: X+61, Y+12
Button B: X+61, Y+93
Prize: X=8174, Y=9384

Button A: X+54, Y+23
Button B: X+15, Y+84
Prize: X=4377, Y=5512

Button A: X+55, Y+24
Button B: X+13, Y+48
Prize: X=1350, Y=272

Button A: X+94, Y+17
Button B: X+65, Y+83
Prize: X=13298, Y=8532

Button A: X+58, Y+82
Button B: X+73, Y+22
Prize: X=6667, Y=7558

Button A: X+73, Y+46
Button B: X+21, Y+69
Prize: X=7153, Y=8188

Button A: X+70, Y+22
Button B: X+47, Y+89
Prize: X=8181, Y=5763

Button A: X+49, Y+17
Button B: X+17, Y+36
Prize: X=12882, Y=18606

Button A: X+70, Y+28
Button B: X+14, Y+52
Prize: X=3766, Y=5636

Button A: X+27, Y+71
Button B: X+89, Y+59
Prize: X=1525, Y=3135

Button A: X+43, Y+15
Button B: X+23, Y+44
Prize: X=9914, Y=3418

Button A: X+15, Y+58
Button B: X+67, Y+23
Prize: X=5115, Y=8649

Button A: X+47, Y+68
Button B: X+24, Y+11
Prize: X=3464, Y=12221

Button A: X+36, Y+87
Button B: X+76, Y+25
Prize: X=7856, Y=5816

Button A: X+93, Y+28
Button B: X+37, Y+66
Prize: X=11030, Y=8368

Button A: X+13, Y+89
Button B: X+60, Y+15
Prize: X=4751, Y=5218

Button A: X+13, Y+24
Button B: X+83, Y+25
Prize: X=3864, Y=2389

Button A: X+71, Y+14
Button B: X+18, Y+67
Prize: X=12041, Y=14559

Button A: X+17, Y+55
Button B: X+62, Y+30
Prize: X=4799, Y=3585

Button A: X+93, Y+18
Button B: X+13, Y+62
Prize: X=7497, Y=3414

Button A: X+25, Y+70
Button B: X+76, Y+50
Prize: X=4854, Y=4800

Button A: X+29, Y+54
Button B: X+48, Y+28
Prize: X=16161, Y=7766

Button A: X+84, Y+90
Button B: X+74, Y+11
Prize: X=11694, Y=8637

Button A: X+41, Y+13
Button B: X+20, Y+81
Prize: X=773, Y=1141

Button A: X+27, Y+91
Button B: X+71, Y+24
Prize: X=8296, Y=10091

Button A: X+50, Y+47
Button B: X+17, Y+67
Prize: X=5512, Y=7018

Button A: X+47, Y+94
Button B: X+76, Y+13
Prize: X=6207, Y=6993

Button A: X+57, Y+12
Button B: X+29, Y+72
Prize: X=7908, Y=13772

Button A: X+11, Y+67
Button B: X+60, Y+21
Prize: X=10797, Y=9382

Button A: X+13, Y+68
Button B: X+76, Y+68
Prize: X=5915, Y=9520

Button A: X+20, Y+55
Button B: X+64, Y+14
Prize: X=3528, Y=2898

Button A: X+72, Y+59
Button B: X+17, Y+68
Prize: X=3139, Y=3167

Button A: X+24, Y+53
Button B: X+38, Y+21
Prize: X=7916, Y=17887

Button A: X+93, Y+97
Button B: X+13, Y+99
Prize: X=9641, Y=15353

Button A: X+15, Y+97
Button B: X+89, Y+34
Prize: X=2353, Y=6010

Button A: X+78, Y+39
Button B: X+58, Y+90
Prize: X=6764, Y=8445

Button A: X+12, Y+43
Button B: X+65, Y+11
Prize: X=5332, Y=5298

Button A: X+71, Y+17
Button B: X+60, Y+57
Prize: X=6585, Y=2472

Button A: X+42, Y+16
Button B: X+28, Y+38
Prize: X=1568, Y=1144

Button A: X+53, Y+24
Button B: X+21, Y+92
Prize: X=4564, Y=3964

Button A: X+18, Y+26
Button B: X+54, Y+12
Prize: X=2862, Y=1098

Button A: X+96, Y+36
Button B: X+24, Y+79
Prize: X=4944, Y=3114

Button A: X+59, Y+21
Button B: X+30, Y+72
Prize: X=19614, Y=428

Button A: X+71, Y+11
Button B: X+64, Y+83
Prize: X=5870, Y=4856

Button A: X+20, Y+54
Button B: X+65, Y+41
Prize: X=5645, Y=3809

Button A: X+38, Y+16
Button B: X+30, Y+66
Prize: X=19304, Y=12482

Button A: X+54, Y+11
Button B: X+36, Y+78
Prize: X=11870, Y=7567

Button A: X+60, Y+35
Button B: X+15, Y+66
Prize: X=1590, Y=6309

Button A: X+12, Y+33
Button B: X+66, Y+48
Prize: X=8792, Y=15647

Button A: X+79, Y+21
Button B: X+68, Y+91
Prize: X=6290, Y=5537

Button A: X+30, Y+21
Button B: X+36, Y+88
Prize: X=5298, Y=8607

Button A: X+59, Y+61
Button B: X+16, Y+70
Prize: X=1747, Y=5869

Button A: X+67, Y+22
Button B: X+15, Y+56
Prize: X=556, Y=3330

Button A: X+93, Y+37
Button B: X+14, Y+61
Prize: X=9946, Y=8724

Button A: X+40, Y+11
Button B: X+29, Y+51
Prize: X=990, Y=3218

Button A: X+19, Y+38
Button B: X+47, Y+11
Prize: X=18259, Y=898

Button A: X+22, Y+13
Button B: X+20, Y+47
Prize: X=2862, Y=4068

Button A: X+67, Y+22
Button B: X+41, Y+46
Prize: X=3326, Y=2296

Button A: X+66, Y+33
Button B: X+25, Y+59
Prize: X=13602, Y=4921

Button A: X+95, Y+51
Button B: X+18, Y+85
Prize: X=6537, Y=10591

Button A: X+20, Y+95
Button B: X+50, Y+24
Prize: X=3730, Y=4694

Button A: X+47, Y+89
Button B: X+42, Y+14
Prize: X=4510, Y=6050

Button A: X+46, Y+41
Button B: X+13, Y+51
Prize: X=3478, Y=6253

Button A: X+27, Y+60
Button B: X+58, Y+17
Prize: X=505, Y=19167

Button A: X+13, Y+16
Button B: X+54, Y+14
Prize: X=5609, Y=2654

Button A: X+20, Y+92
Button B: X+64, Y+34
Prize: X=2852, Y=1922

Button A: X+25, Y+78
Button B: X+72, Y+21
Prize: X=3901, Y=17510

Button A: X+11, Y+57
Button B: X+73, Y+20
Prize: X=8507, Y=9590

Button A: X+81, Y+82
Button B: X+59, Y+15
Prize: X=9617, Y=6068

Button A: X+17, Y+51
Button B: X+63, Y+32
Prize: X=10215, Y=8955

Button A: X+23, Y+62
Button B: X+43, Y+16
Prize: X=12373, Y=18934

Button A: X+70, Y+27
Button B: X+17, Y+96
Prize: X=2196, Y=2457

Button A: X+72, Y+15
Button B: X+18, Y+71
Prize: X=17450, Y=4395

Button A: X+23, Y+49
Button B: X+82, Y+29
Prize: X=3918, Y=3102

Button A: X+15, Y+89
Button B: X+34, Y+40
Prize: X=1318, Y=6688

Button A: X+32, Y+54
Button B: X+46, Y+21
Prize: X=9480, Y=6938

Button A: X+13, Y+46
Button B: X+50, Y+12
Prize: X=9407, Y=11642

Button A: X+18, Y+56
Button B: X+78, Y+67
Prize: X=7974, Y=10579

Button A: X+16, Y+62
Button B: X+94, Y+55
Prize: X=6480, Y=7792

Button A: X+89, Y+35
Button B: X+38, Y+83
Prize: X=9427, Y=7042

Button A: X+17, Y+80
Button B: X+87, Y+43
Prize: X=9527, Y=9291

Button A: X+29, Y+14
Button B: X+29, Y+52
Prize: X=1540, Y=15370

Button A: X+22, Y+53
Button B: X+46, Y+22
Prize: X=1192, Y=5223

Button A: X+24, Y+61
Button B: X+43, Y+18
Prize: X=15889, Y=4821

Button A: X+59, Y+56
Button B: X+17, Y+85
Prize: X=1683, Y=4352

Button A: X+15, Y+37
Button B: X+44, Y+21
Prize: X=2840, Y=3504

Button A: X+34, Y+83
Button B: X+44, Y+32
Prize: X=5074, Y=7711

Button A: X+17, Y+41
Button B: X+18, Y+12
Prize: X=1736, Y=4664

Button A: X+13, Y+23
Button B: X+79, Y+24
Prize: X=1442, Y=1162

Button A: X+59, Y+22
Button B: X+14, Y+44
Prize: X=1493, Y=4202

Button A: X+50, Y+24
Button B: X+50, Y+90
Prize: X=4550, Y=5814

Button A: X+42, Y+76
Button B: X+88, Y+45
Prize: X=7484, Y=8516

Button A: X+54, Y+30
Button B: X+19, Y+57
Prize: X=5507, Y=3849

Button A: X+43, Y+90
Button B: X+67, Y+12
Prize: X=913, Y=1398

Button A: X+71, Y+11
Button B: X+37, Y+97
Prize: X=4860, Y=4860

Button A: X+29, Y+64
Button B: X+99, Y+18
Prize: X=3808, Y=986

Button A: X+42, Y+76
Button B: X+33, Y+11
Prize: X=19352, Y=13554

Button A: X+15, Y+72
Button B: X+99, Y+60
Prize: X=6456, Y=4416

Button A: X+44, Y+15
Button B: X+59, Y+76
Prize: X=4939, Y=3528

Button A: X+27, Y+20
Button B: X+23, Y+97
Prize: X=1796, Y=4289

Button A: X+20, Y+69
Button B: X+31, Y+28
Prize: X=3083, Y=4873

Button A: X+64, Y+11
Button B: X+20, Y+59
Prize: X=13820, Y=19638

Button A: X+85, Y+21
Button B: X+35, Y+65
Prize: X=2900, Y=3196

Button A: X+37, Y+14
Button B: X+47, Y+69
Prize: X=19753, Y=12121

Button A: X+84, Y+11
Button B: X+89, Y+94
Prize: X=9052, Y=7773

Button A: X+59, Y+27
Button B: X+28, Y+49
Prize: X=6811, Y=6048

Button A: X+11, Y+41
Button B: X+53, Y+29
Prize: X=15684, Y=1596

Button A: X+74, Y+60
Button B: X+17, Y+81
Prize: X=846, Y=2568

Button A: X+37, Y+19
Button B: X+17, Y+36
Prize: X=418, Y=2575

Button A: X+66, Y+52
Button B: X+23, Y+80
Prize: X=4669, Y=3988

Button A: X+15, Y+83
Button B: X+84, Y+12
Prize: X=2861, Y=18617

Button A: X+86, Y+38
Button B: X+13, Y+60
Prize: X=11941, Y=224

Button A: X+16, Y+83
Button B: X+68, Y+29
Prize: X=7384, Y=6577

Button A: X+19, Y+63
Button B: X+41, Y+18
Prize: X=3115, Y=2898

Button A: X+78, Y+36
Button B: X+34, Y+60
Prize: X=8960, Y=5952

Button A: X+16, Y+62
Button B: X+59, Y+14
Prize: X=4197, Y=7846

Button A: X+61, Y+91
Button B: X+75, Y+31
Prize: X=835, Y=1003

Button A: X+22, Y+79
Button B: X+91, Y+65
Prize: X=2987, Y=7323

Button A: X+57, Y+13
Button B: X+23, Y+58
Prize: X=14865, Y=12512

Button A: X+57, Y+69
Button B: X+77, Y+15
Prize: X=6139, Y=2817

Button A: X+91, Y+56
Button B: X+25, Y+69
Prize: X=7126, Y=7763

Button A: X+16, Y+35
Button B: X+30, Y+17
Prize: X=3366, Y=4008

Button A: X+67, Y+24
Button B: X+27, Y+86
Prize: X=6782, Y=8154

Button A: X+12, Y+49
Button B: X+54, Y+14
Prize: X=4862, Y=12478

Button A: X+14, Y+56
Button B: X+40, Y+19
Prize: X=5836, Y=10204

Button A: X+22, Y+72
Button B: X+69, Y+11
Prize: X=6915, Y=5523

Button A: X+28, Y+83
Button B: X+68, Y+11
Prize: X=14848, Y=2920

Button A: X+44, Y+11
Button B: X+22, Y+60
Prize: X=16336, Y=18195

Button A: X+62, Y+52
Button B: X+24, Y+79
Prize: X=5746, Y=9941

Button A: X+18, Y+67
Button B: X+66, Y+50
Prize: X=912, Y=2025

Button A: X+92, Y+23
Button B: X+26, Y+79
Prize: X=4826, Y=3889

Button A: X+14, Y+49
Button B: X+62, Y+31
Prize: X=16240, Y=4860

Button A: X+42, Y+11
Button B: X+25, Y+78
Prize: X=11083, Y=7794

Button A: X+43, Y+32
Button B: X+19, Y+61
Prize: X=4387, Y=6873

Button A: X+83, Y+59
Button B: X+21, Y+51
Prize: X=2245, Y=2173

Button A: X+25, Y+12
Button B: X+14, Y+36
Prize: X=12142, Y=1808

Button A: X+25, Y+11
Button B: X+18, Y+50
Prize: X=1601, Y=2051

Button A: X+20, Y+57
Button B: X+70, Y+25
Prize: X=11590, Y=937

Button A: X+70, Y+29
Button B: X+15, Y+56
Prize: X=15205, Y=4381

Button A: X+19, Y+81
Button B: X+86, Y+41
Prize: X=10300, Y=11673

Button A: X+51, Y+75
Button B: X+67, Y+15
Prize: X=2803, Y=2535"""