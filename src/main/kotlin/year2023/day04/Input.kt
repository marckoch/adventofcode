package year2023.day04

const val SAMPLE = """Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"""

const val INPUT = """Card   1: 33 56 23 64 92 86 94  7 59 13 | 86 92 64 43 10 70 16 55 79 33 56  8  7 25 82 14 31 96 94 13 99 29 69 75 23
Card   2: 61 66 75  1 27 38 93 90 34 43 | 94 46 62 49 35 88 45 70 15 22 20 86 56 38 64 98 50  6 79 11 13 93 92 60 16
Card   3: 57  7 33 56 85  6 88 34 80  8 | 92 42  7 60 61 51 40  6 67 35  3 25 87  2 98 75 97 54 10 68 73 83  4 62 56
Card   4: 79 85 94 74 15 62 84 88 76 56 | 56  9 22 57  4 92 62 79 84 64 72 55 34 88 66 15 45 18 76 73 85 94  8 78 74
Card   5: 57 94 99 25 52 67 69 31 26 78 | 94 52 31 83 70 45 40 67 89 11 81 24 25 61 26 72 50 12 27 69 91 57 55 34 78
Card   6:  5 96  3 19 98 25 13 59 94  8 | 36 55 22 76 86 19 10  8 59  9 87 40  2 71 13 98 12 77  3 70  5 25 34 41 88
Card   7: 35 52 84 36 72 53 76 88 41 14 | 57 34 14 39 44 71 51  1 67 30 16 77 23 66 45 74 37 55 38 69 33 31 98 72 36
Card   8:  7 70 72 13 23  1 48 18 40 94 | 48 70 93 99 20 23 17 40 72 35 21  7 71  3 42 59 87 55 18 41 94  1 13 22 90
Card   9: 40  2 46 38 86 16 62 78 29 13 | 26 46 47 29 99 51 25 57 66 86 62  2 22 70 41  3 78 13 74 15 16 90 43 40 38
Card  10: 35 71 99 87 81 58  5 83 55 73 | 90 34 71 10 96 38 39 29 69 93 35 51 86 12 76 91 80 36 17 59 64 68 58 15 82
Card  11: 35 89 27 73 65 46 39 86 81 90 | 86 90 50 35 73 31 92 65 18 81 30 37 21 76 89 56 64 71 49 12 27 82 16 32 29
Card  12: 15 77 35 41 38 93 63 30 39 18 | 90 69 65 93 13  4 64 51 72 57 96 91 75 14 58 94 28 38 63 97 86 84 50 15 21
Card  13: 10 82 16 85 74 38 95 51 54 94 | 66 29 85 73 54  8 51 14 56 74 46 42 10 67 16 59 23  7 95 48 94  6 82 68 88
Card  14: 77 53 62 72 97  7 36 96 67 28 | 30 24 28 44 39 77 15 88 92  4 60 66 11 21 20 42 55 53  6 12 95 87 37 58 85
Card  15: 89 74 36  8 27 73 90 60 48 56 | 56 45 74 78 39  7 15  6 89 88  8 76 90 16 22 36 17 10 99 79 71 59 46 96 49
Card  16: 82 64 99 10 32 65 20 78 29 31 | 49 59  4 78 22 18 95 82 54 72 39 41 35 14 98  1 84 92 58 64 28 83 50  7 65
Card  17: 25  1 40 66 84 24 19 17 10 46 | 22 40  8 87 17 38  6 95 36 51 15 93 18 73 56  9 13 57 63 10 78 37 48  1 84
Card  18: 18 14 27 40 80 47  9 65 22  5 | 90 59 72 36 33 31 93 55 75  3 56 37 27 87 10 23 47 19 99 85 35 48 18 62 69
Card  19: 63 16 71 14  1 89 61 55 62 44 | 32  5 64 82 94 77 11 90 54 47 49 29 97 78 57 68 92 33 44 28 59 30 72 18  8
Card  20: 60 21 85  8 35 66 70 36  2 58 | 10 37 36 64 72 98 60 19 55 45 30 33 31 94 90 49 71 57 81 17 91 29 68 86 39
Card  21:  4 25 18  6 56 62 97  1 83 30 | 42 66 83 75 14 50 26 24 90 36 46 87 49 84 53 65 80 17 92 70 19 95  9 27 32
Card  22: 53 91 82 19 43 83 65 46  4 85 | 97 67 90 39 16  1 54 64 10 77 99 71 28 94 30 45 84 95 21 35 13 61 29 11 33
Card  23: 65 66 83 69 23 16 13  3 29 68 |  6 54 27 65 16 68 13 69 29 14 91 23 37 61 39 74 66 77 83 11 26 40 92  3 49
Card  24: 43 97 61 35 69 20 65  3 23 79 |  3 69 20 80 98 92 18 61 91 96 86 88 19 25 43 97 17 79 47 55 11 35 23 77 65
Card  25: 94 30 47 27  2 80 76 75 82 67 | 28 12 96 27  2 20  4 29 58 18 93 75 62 38 30 72 94 80 76 91 47 14 67 82 46
Card  26: 51 47 45 64  9 53 16 80 61 94 | 88 28 84 45 61 51 70 18  4 21 94 62  5 53 32 10 20 86 47 46 43  9 66 83 80
Card  27: 31  5 15 38 10 61 33 92 26 47 | 60 49 54 69  1 99 85 29 95 34 84 81 36 11 57 67 14 55 90 51 17  7 37 35 48
Card  28: 25 69 85 59 82 16  6 17 49 62 | 80 17 62 69 16 46 87 29 59 64 97 85 45 30  6 82 47 75 25 43 72 14  2 23 49
Card  29: 58 97 36 33 62 27 74 38 68 23 | 51 20 85 47 44 67 48 25 39 36 17 61 52 22 79  6 64 57 95 56 71 33 98 34 42
Card  30: 74 96 56 34 64 54 26 22 62 59 | 32 74 61  7 34 47 83 94 27 26 66 54 87 75 56 65 49 13 64 70 96 62 53 22 59
Card  31: 26  6 14 82 12 60 16  4 92 87 | 16 33 76 55 54 39 27 26 17 83  6 18 94 77  3 40 81 92  1 69 25 19 71 99  2
Card  32: 15  3 33 16 13 65  8 44 40 96 | 22 15 46 89 16 50  6 33 53 24 96 40  8 35 97 13  3 55 43 14 65 66 34 60 44
Card  33: 41 61 28 60 85 69 87 62 91 18 | 49 88 84 73 20 45 75 99 27 25  3 65 66 30 50 54 97 57 76 96  2  6 39  5 18
Card  34: 12 72 20 48 49 77 38 86 68 92 |  6 56 30 68 95 87 42 16 59 10  7 22 82 74  2 71 19 48 50  1 40 37 24 80 72
Card  35: 97 86 21 45 10 30 63  8 36 91 | 33 31 67 77 29 24 10 79 21 25 12 71 30  1 68 56 46 94 51 64  8 14 45  4 41
Card  36: 90 75 80 95 49 87  2 88 50 42 | 77 85 53 76 52 72 32 42 79 65 50 25 19 14 37 11 35 63 29 75 49 98 96 54 95
Card  37: 19 84 30 59 86 49 31 40 14 50 | 20 92 36 43 82 18 86 47 73 30 84 19 99  5 48 34 68 63 61 46 53 75 16 28 45
Card  38: 39 47 28 13 75 89 76 93 15 14 | 36 94 51 97 49 16 66 60 72 30 52  5  3 17 23  7 58 14 63 87 54  8 56 13 35
Card  39: 70 22  9 80 89 51 43 64 57 37 | 23 39  5 27 98 11 29 73 72 10 63 79 59 58 46 96  2 86 50 19 67 41 95 66 82
Card  40: 94 95  1 39 64 63 54 19 17 38 | 44 53 12 92 11 34 42 67 93 36  6  3 29 60  7 62 85  4 33 83  2  9 10 82 51
Card  41: 69 12 36 48 67  7 52 89 63 73 | 11 61 81 93 47 20 27 31 66 64 45 38  2 59 46  5 73  7 87 22  6 52 36 10 54
Card  42: 17 93  3 90 91 59  6 57 54 65 | 22 17 99 16  6 77 38 46 43 59 41 47 12  2 86  4 40 56 80 82 11 98 23 20 85
Card  43:  1 68 36 70 24 83 86 94 52  7 | 38  9 64 12 10 60 92 81 77 98 59 23 79 91 65 28 13 15  6 69 24 40 19 16 99
Card  44:  3 45 25  7 57 67 62 36 40 44 | 51 23 88 52 68 11 89 84 59 54 10 77 73 96 46 44 17 85 91 12 80 74  5 27 39
Card  45: 14 68 88 27 44 83 37 22 65  7 | 78 61 91 48 92 21 52 49 77 74 46 24 33 28 36 89 53 39 93 23 72 95  2 63 67
Card  46: 20 59 75 43 98 38 85 46 74 57 |  3 34 11  7 98 64 52 89 43 41 87 57 19 56 37 68 20  2 99 91 23 17 74 48 78
Card  47: 87 20 73 47 82 37  3 68 29 65 | 15 53 58 25 62 13 31 59 11 63  2 89 30 71 35 36 82 37 75 67 17 12 34 90 19
Card  48: 63 11 55 53 44  7  3 41 60 40 | 73 61 87 91 46 60 51 31 11 32  7 44 41 78 55 14 59  3 63 53 74 94 62 65 40
Card  49: 30 62 35  9 40 51 68 55 79 97 | 53 87 77 97 63 11 52 67 51 35 68 79 99 98  5  9 40 37 19 49 30 16 55 75 62
Card  50: 66 80 74 99 63 84 35 26 83 67 | 43 92 52 67 63 84 16 21 88 23 31 22 24 49 59 99 75 47 66 80  1 83 91 95 70
Card  51: 68 99 70 39 79 10 81 27 46 80 | 70 17 46 68 61 50 43 48 79 81 12 32 65  1 27 39 80 99 58  8 19 10 41  4 73
Card  52: 37 86 40 43 66 14 63  2 19 50 | 96 50 27 43 64 83 13 92 63 86 37 19  2 40 58 66  3 31 59 14 62 61 67 54 53
Card  53: 78 33 11 45 12 22 19 38 87 74 |  2 50 63 33 97  8 58 99 48 22  4 45 11 60 74 21 69 78 87 29 12 39 19 18 38
Card  54: 11 51 64 21 19 87 70 80 55 95 | 11 69 53 94 24 96 87 59 95 76 23 19 68 47 51 74 70 55 32 77 80 64 21 36 78
Card  55: 92 99 88 93  1 26 13  4 61 11 | 71 92 99 61 76 56 46 89  4 67  3 45 22 81 19 33 50 26 93 28 13 72  1 11 88
Card  56: 11 65 76 35 97 63 16 57 92 53 | 96 30 65 82 76 59 63 97 35 40 16 98 86 79 41 11 78 95 10 88 29 28 64  5 74
Card  57: 32  5  1  8 43 15 25 79 96 71 | 25 49 31 90 39 77 27 83 15 56 41  5  1 26 58 33 34 79 62  8 96 32 92 43 71
Card  58: 65 20 54 93 18 31 97 99 94 86 | 89 46 12 27 23 42 43 88 75 73 35 49 95  9 37 66 50 62 51 48 11 17 26  1 41
Card  59: 62 38 28  4 59 53 16 89 52 35 | 27 47 52 12 35 86 59  8 39 31 81 55 95 79 37 89 58 14 30 80 68 87 43 84 56
Card  60:  8 89 16 22 75 94 34 67 78 62 | 84 81 29 51 10 70 91 95 32 34 46 12 67 66 68 99 74 87 92 78 90 13 76 18 27
Card  61: 27 92 80 94 87  2 75 26 22 79 | 36 34 76 84 22 48 52 86 79 46 81 80 87 78 27 95 70 51 35 49  9 89 75  8 67
Card  62: 89 10 56 52 80 76 46 31 69 24 | 73  6 53 98 17 65 44 16 83 45 92 41 94  3 81 99 26 61 43 67  2 93 36 28 29
Card  63: 38 74 68 99 88  4 42 87 58 31 | 39 99 22 11 16 50 52 53 85 34 67 76 55 42 45  8 89 43  2 20 75  4 97 28 70
Card  64: 22 38 75  7 35 62 49 30 10 32 |  1 54 59 40 12 74 46 51 37 21 72 57 97 82 31 25 87 47 29 43 67 88 19  9  8
Card  65: 21 69 41 76 88 73  4 77 34 93 | 92 53 25 78 81 84  8 49 77 41 28 70 12 88 91 45 29 15 86 46 32 96 57 34 54
Card  66: 42 60  7 35 31 50 13 61  9 19 | 53 18 80 69 78 88 24 56 29 62 49 95 64 81 11 42 17 40 34 28  6 98 70 54 91
Card  67: 36 50 47 89 18 54  9 25 92 91 | 87 59 85 42 62 37 26 21 52  4 90 19 75 23 67 33 35 49 69 79 14 80 46 97 98
Card  68: 92 43 44 34 29 75  6  1  8 31 | 88 85 55  9  1 95 14 97 67 42 22 72 89 19 90 35 74 96 58  5 57 51 47 37 69
Card  69: 59 43 21 14  5 85 20 12  1  7 | 40 11 63 87 72 34 83 86 94 31 66  3 24 98 82 39 65 35 51 77 67 18  2 47 90
Card  70: 24 83 87 85  3 48 99 11 21 33 | 76 92 16 81 61 86 83 14 57 44 74 41 98 63 96 38 89 65 32 11 48 51 53 23 19
Card  71: 57 77 65 66 16 50 49 39 55 87 |  3 45 97 44 31 14 25 70 13 72 16 41  2 66 88 69 57 26 54 95 80 10 65 28 86
Card  72:  3 80 44 10 43 90 71 20 17 85 | 86 53 46 72 74  3 91 99 19 55 94  2 65  4 36 26 29 16 95 48 11 24 90 76 78
Card  73: 71 11 88  6 54 78 97 30 91 92 | 52 86 23 91 78 54 97 66 11 44 30  8 93 71 16 92 21 83 84 88 34  6 33 26  9
Card  74: 87 88 97 95 33 72 21 39 60 66 | 65 68 95 84 97 32 72 21 43 74 23 55  1 96 92 87 25 66 60 16 33 59 39 98 88
Card  75: 40 81 51 37 56 61 25 84 82  4 | 82 84 43  7 23 61 14 38 63  5 27 85 48 71 56 99 30 96 29 40 18 37 77 13 16
Card  76: 40 46 25 27 39 47 33 37 36  7 | 13 39  9 80 34 38  8  7 60 48 92 71  5 40 10 41 37 21 67 29 25 43 69 28  2
Card  77: 25 10  9 74 66 52  6 22 41  3 | 32 24 86  5 47 22  6 23  3 69 56 40 28 52 54 17 95 53 68 39 60 74 71 99  4
Card  78: 59 18 58 51 88  8 92 27 82 79 | 97 86  8 11 79 28 88 70 48 80 42 37 87  2 92 95 55 59 82 40 50 27 51 68 63
Card  79: 51 97 54 22 64 44 80 27 96 47 | 93 72 29 23 96  1 54 66 51 24 22 62 87 97 35 27 44 77 79 64  3 37 47 45 80
Card  80: 64 67 54 24  8  4 35 89 15 23 | 67 12 13 77 45 15 54 92 89  9 96 95 25 24 99  4 81 52 46 35 69 64 23 79  8
Card  81: 34 25 84  7 23 12 54 30 20  1 | 49 26 82  7 13 75 89 58 53 67 93 46  8 97 32 86 11 72 28 54 98 56  1 45 55
Card  82: 87 24 17 63 67 65 43 97 38 95 |  8 40 49 43 15 36 88 11 83 29 46  7 62 31 51 23 69 67 66 39 54 72 35 80 65
Card  83: 12 57 85 81 48 97 45  5 72 11 | 68 98 58 29  8 22  9 30 49 51 67 47 74 13 40 65 94 44 91 34 92 82 14 33 61
Card  84: 95 33 18 64 24 92 27 29 57 53 | 56 19 14 79 60 36  6 12 75 71 26 87  4 58 54 16 52 31 98 17 85 40 61 41 77
Card  85: 42 71 23 49 53 96 88 48 60 95 | 66 18 98 88 58  7 12 11 89 37 13  1  6 28 45 47 30 31 55 57 17 97 77 99  4
Card  86: 44 31 35  2 49 88 26 98 43 42 | 19 46 70  1 92 12  3 11 30 74 33 38 95 17 24 75  9 28 83 32 40 65 62 10 29
Card  87: 98 23 88 50  4 73 92  5 11 24 | 85 14 59 94 41 69  5 99 46 22 27 61 77  7 60 95 58  6 30 24 44 92 10 88 23
Card  88: 39 61  4 84  2  6 33 53 85 42 |  3 77 16 69 11 76 48 80 95 15 12 10 83 78 19 57 30 56 52 46 58 88 55 35 13
Card  89: 27 97 32 38 46  3 62 40 58 89 |  6 91 80 72 21 56 24 50 82 54 74 97 60 73 47 96 13 38 84 12  9 69 14  3 41
Card  90: 80 50  8 55 93 29  3 87 84 27 | 38 83 96 69 76 28 18 87 32 45 89 48 82 91 70  6 25 24 73 42 19 49 34 51 93
Card  91: 10 40  8 20 83 79 47  2 69 12 | 31 79 74 71 87 57 41 36  7 22 76 42 82 72 16 78  3 67 43 52 26 85 39 25 14
Card  92: 72 76 71  8 20 13 48 25 81 47 | 33 94  7 85 64 93 53 31 10 21 92 50 70 23  1 99  2 12 18 97 66 45 57 51 55
Card  93: 50 21 56 54 41 32 77 19 93 13 | 36 88 97 56 53 99 68 26 13 21  9 86 77 41 50  3 16 75 98 48 24 62  8 32 93
Card  94: 52 39 66 88 19 43 80 53 33  3 | 66 34 43 41 65 92 80 50 72 86 94 69 52 53 39 67 83 16 25 19 99 93 11 33  6
Card  95: 22 38 17 63 68 51 79 72 81 61 | 79 63 38 71 81  1 78 13 68 33 21 51 12 57 53 67 97 22 62 72 61  2  9 17 73
Card  96: 83 96  2 47 21 68 50 78 19 29 | 77 66 37 59 36 96 74 16 80 84 65 87 90 58 89 48 42 47 68 85 35 49 56 40 19
Card  97: 72  8 69 10  3 33 79  4 99 65 | 26 99 96 10 31 33  8 58 38  4 40 69  6 97 80 79 98 65 20 57 73  3 81  5 72
Card  98: 43 67 24 40 95  3 27  1 89 39 | 24 87 37  1 26 72 19 95 43 89 32  3 27  5 55 40 25 71 69 67 39 74 56 93 35
Card  99: 68 24 93 30 34 20 27 89 37 50 | 93 77 86 17  4 30 60 37 98 24 68 34 14 39  8 31 18 50 48 10 20 12 27 78 70
Card 100: 68 45 61 38 58 50 27 87  9 96 |  3 23 52 54 76 49 34  8 81 78 11 38 33 15 26 40 37 67 94 51 24  5 30 97 31
Card 101: 23 16 99 80 29 13 81 67 27 22 | 94 53 95 19 66 31 96  3 60 28 38 30 35 47 44 68 75 82 56 18  5 50 36 78 64
Card 102: 17  2 85 30 78 23 93 46 88 92 | 18 44 84 92 13 62 48 46 53 31 52 17 93 78 29 51 99 39 30 23 91  5 81  2 33
Card 103: 35 21 66 81 40 75 50 88 14  6 | 16 25 61 81 13  6 17 77 38 66 98 41 45 54 23 19 99 21 49 32 51 35  8 47 65
Card 104: 29 37 46 77 92 53 81 20  9 96 | 94 81 75  8 24 78 68 48  4  5 98 16 30 57  3 10 14 70 36 33 91 64 47 18 87
Card 105: 15  2 31 71 99 30 26 61 79 32 | 21 49 58 16 67 94  5 75  4 69 96 51 28 53 17 89 25 85  1 12 83 82 10 33  6
Card 106: 51 24 14 80 10 38 53 55 41 32 | 69 66 91 73 65 12 36 75 45 90 46 58 80 57 74 28 39 37 95 22 30 62  7 32  1
Card 107: 26 78 66 42 54 69 51 95 17 52 | 64 69 20 75 87 30 11 42 60 47 85 46 80 36 40  7 94 68 33 21 31 83 89 10 82
Card 108: 82  2 99 56 80 38 54 47 20 29 | 23 56 40 97  5 81 37  4 69 48 91 64 58 73 32 61 54 78 77 43 67 14 17 68 15
Card 109: 97 80 18 44 73 53 15 50 47 48 | 30 41 82 36 43 86 95 56 33  4 89 47 28 59 27 31 92 12 93 32 60 74 99 26 75
Card 110:  4 58 43 91 66 59 69 73 37 94 | 35  3 65 27 77 52 44 38 86 79  2 32 56 84 10 60 18 24 49 64 61 78 93 99 22
Card 111: 52 86 22  5 18 14  7 92 65 56 | 50 86 28 52 14 48 56 93  5 22 80 76 65 25 92  2 78  7 45 49 70 47 18 15 53
Card 112: 10 36 28 87 20  7 93 15 65 53 | 27 93 36 83 87 46 24  1 86 43 28  7 89  4 32 20 38 14 15 67 57 29 69 40 10
Card 113: 27 38 49 70 32 56 22 11 43 10 | 25 75 85 29 63 83 35 95 65 15 84 49 87 27 74 43 11 13 46 10 72 56 22 70 57
Card 114: 91 82 29 32 44 84 51 67 94 11 | 51 69 55 28 89 74  8 96 93 35 42 33 73 44  3 31 36 68 38 11 94 46 29 12 82
Card 115: 88 46 87 97 52 18 20 96  2  3 | 17 36 23 42 67 93 72 24 10 89 87  8 66 48 25 50 61 32 59 52 84  2 35 99 65
Card 116: 50 23 72 60 40 77 97 90 98 70 |  3 76 97 92 72 11 28 60 30 29 35 45 70 62 84 55 57 87 22 14 38 69 51 25 86
Card 117: 31  4 58 33 86  7 47 25 62 87 | 44 36  7  4 73 84 25 58 65 14 23 96 55 31 27 87 77 17  9 15 47 22 98 69 62
Card 118: 71 33 83 75 28 82 56 94 21 42 |  1 21 43 28 13 86 19 82 58 48 89 94 71 91 41 95 27 79 73 93 75 31 66 30 83
Card 119: 38 69 51 58 93 60 13 66 90 71 | 44 94 98 53 49 69  9 74 24 57 71 45 12 78 18 56 88 68 85 42 35  1 47 17  2
Card 120: 44 82 72 71 73 16 39  5 93 81 | 57 21  8 33  3 69 54 47 26 70 53 10  5 44 77 31 29 87 24 19 93 37 80 65 84
Card 121: 90 63 79 82 91 85 98 70 14 28 | 66 71 82 27  2 29 44 73 76 18 47  3 55 64 34 25  6 88 81  8 30 19 75  1 31
Card 122: 80  4  6 16 18 20 23 35 50 90 | 50 25 27  1 70 12 60 19 96 37 57 82 69 89 45 87 23 26 75 90 48 42 30 78 54
Card 123: 32  6  8 96 56 86 73 18 71 92 | 35 64 83 21 15 95 49 80 33 93 11 45 29 78 68  5 60 63 86 67 57 91 42 51 97
Card 124: 72 84 64 25 99 28 40 98 96 59 | 31 50 67 35 94  3 10 95 85 38 46 45 80 42  6 62 97 52  7 92 83 44 55 65 21
Card 125: 13 63  2 53 48 38 88 14  5 58 | 49 50 91 75 40 66 34 71 24 52 28 26 55 19 25 73 98 56 11 41 96 21 85 67 29
Card 126: 55 36 33 58 17 53 39  6 21 52 | 88 30 68 84 61 14 17 97  7 19 99 54 45 56 52 35 92 50 85 36 24 70 27 29 75
Card 127: 96 37 56 99 84 12 10 70 93 55 | 85 77 82 60 74 20 51 27 92 12 59 98 91 81 56 31  6 53  4 10 89 84 90 69 55
Card 128: 63 44 56 61 88 65 33 85 81 55 | 58 28 88 75 33 89 20  8 61 37  6  9 19 92 46 17 35 94 29 62 76 41 55 57 80
Card 129: 86 99 43 13 11 17 67  9 50 33 | 47 67  6 69 11  5 13 98 96 89 57 40 61 79 33 80 15 42 62 92 59 86 10 65 44
Card 130:  4 71 54 22 81  7 25 19 29 50 | 91 16 36 54 51 19 50 80 83  7 81 64 61 30 85 72 79  9 13 12 67 59  4 31 20
Card 131: 87 91 50 20  3 77 14 47 97 76 | 71 87  8 34 78 27 32 61 33 89 62 52 72 17 60 16 42 51 64 49 66 86 22 30 59
Card 132: 68 17  4 26  8 14 41 57 21 31 | 98 31 75 47  9 48 30 65 36 40 70 57 77 72 76  3 21 42 68 24 96  7 23 17 10
Card 133: 46 32 98 75 52 49 80 60  9 47 | 87  3 69 96 29 80 54 98 53 49 66 88 35 75 58 40 93 73 42  2 30 90  1 76 22
Card 134: 23  8 93 17  5 21  9 19 13 82 | 71 37 95 91 65 43 27 44 36 24 98 99  2 74 20 88 49 17 76 80 46 57 79 94 81
Card 135: 81 28 19  5 22 75 18 74 51 13 | 32 66 55 62 17 60 96 70 23 37 34 67 14 84 24 76 16 47 94 56 38 65 25 97 98
Card 136: 72  8 16 42 50 75 57 39 82 41 | 86 82 57 11 62 92 79 78  1 41  3 64 15 12 46 31 89 45 63 91 81 47 96 27 10
Card 137: 64 70 10 41 33 73 22 62  9 21 | 25 47 90 79 14 97 80 34 33 40  4  6 64 28 83 91 73 63 37 44 98 42  5 12 62
Card 138: 87 14 61  6 27 82 35  8 54 56 | 95 36 51 60 98 78 30 83 19 58 72  4 88 10 77 69 96  8 87 74 27 92 65 75 35
Card 139:  4 16 59 44 74 86 33  5 95 60 | 14  2 34 29 44 26 12 70 66 47 67 52 85 71 95  7 11  4 30 77 40 96 36 98  6
Card 140: 12 86  6 69 88 43 13 55 81 10 | 19 71  6 51 72 46  2 97  3 67 56 74 90 42 86 28 32 52 98 17 38 76 77 49 10
Card 141: 29 72 27 68 17  7 45 64 49 26 | 35 11 29 26 66 14 41 52  6 42 92  5 99 39 24 59 75 12 83 51 78 58 28 81 93
Card 142: 41 63 11 16 15 59 97 34 40 23 | 39 85 37 84 30 20 77 60 36 19 42 61 11 67 10 21 99 87 32 66 48  1 28  5  9
Card 143: 47 14 63 53 73 40  7 50 15 21 | 69 96 26 94 38  9 10 79 78 48 82 66 59 57 12 37 74  1 92 98 88 42 31 39 23
Card 144: 36 88 96 60 86 29  1 57 37 46 | 99 47 22 57  3 60 29 87 86 36 58 30 40 37  1 33 88 49 46 11 96 80  9  8 82
Card 145: 54 66 15 59 79 52 73 14 23  8 | 50  8 20 14 92 72 98 80 85 77 42 49 59 58 18 91 90 17 21 97  1 19 53 84  2
Card 146: 36 80 57 90 97 28 76 52 77 45 | 41 14 46  8 84 91 20 57 52 99 37 67  3 55 10 87 25 18 43 77 97 80 78 85 74
Card 147: 64 12 80 55 49 67 78 28 20 10 | 20 43  1 78 83 39 80 38 24 54 75 49 94 22 26 67 71 97 64 28 12 45 10 55 89
Card 148: 57 96 75 17 53 63 60  8 95 27 |  8 60  2 82 19  5 24 98 27 93 80 86 63 97 17 53 99 59 70 14 78 96 95 57 75
Card 149: 50 95 82 20 27 47 80 48 13 49 | 87 77 20 82 88 74 94 48 13 99 30 65 47 27 11 49 80 61 71 50 34 62 85 18 95
Card 150: 52 14 22 54 43  3  1 99 73 39 | 42 97 89 92 58  8 60 80 82  5 28 17 35 79 40 50 61 56 53 75 95 25 83 31 15
Card 151: 44  9  3 78 73 55 32 59 70 24 | 75 23 91 37 81 66 24 59 70 38 25 55 54  9  3 73 32 44 89 39 78 64 83 12  7
Card 152: 37 29 16 15 24 41 40 99 21 87 | 37 78 68 17 95 60 69 44 55 21 66 96 82 65 87 29 23 36  4 20 15 52 94 75 74
Card 153: 72 80 39 77 71 29 83 10  7 93 | 10 51 52 12  1 22 65 62 33 32 34 11 13 55 72 57 76 96 85 95 90  3 64 47 31
Card 154: 89 66 34 86  8 75 50 98 56 71 | 74 39 16 68 56 27  5  2 58 97 75 34 90 91  8 71 95 66 50 98 60 92  6  9 35
Card 155: 23 84 27 71 33 15 96 25 14 57 | 11 31 46 14 77 88 27 60 59 93  3 96 48 71 33 15  5  6 23 79 89 82 57 25 84
Card 156: 10 25 83 53 73 33 56 29 95 24 | 22 29 11 32 41 74  4 43 46 38 78 16 91 12 73 54 62 10 25 47 52 65  7 28 63
Card 157: 97 31 12 42 32 23 29 72 98 99 | 64 41  3 57 25 76 46 80 75 47 73 87 91 95 82 55 31 35 51 60 16  8 78 77 66
Card 158: 90 46 61 13 33 59 12 25 30 49 | 29 57 31 46  1 64 93 43 94 21 87 83 23 67 91 52 92 85 71 78 36 79 62  5 58
Card 159: 75 92 63 61 69 53  1 74 51 27 |  3 87 65 74 10 59 54 42 22 95 26 24 67 91 33 55 92 77 98 79 43 82 13 12 27
Card 160: 50 38 97 82 73 27 91 79 74 41 | 69 71 34 94 55 40 23 84 10 25 14 67  7 46 48  6 37 91 12 66 16 17 26 22  9
Card 161: 57 19 25 38 12 76 89 95 10 33 | 31 63 68 46 42 79 94 60  7 44 36 56 91 23 35 85 41 87 71 58 82 54 11  9 81
Card 162: 17 54 37 84 85 46 99 51 86  7 | 32 38  4 66 49  7  9 23 76 35 51 85 52 30 45 19 89  6 82  5 58 91 87 72 50
Card 163: 44  4 10 46 38 87 52 83 85 16 | 79 42 21 58 75  3 25 56  7 50 69 57 27 84 38 39 89 70 59 78 93 85  8 96 72
Card 164: 76 26 66 23 89 37 87  9 34 81 | 84 43 14 21 13 20 95 97  3 33 18 88 81 78 16 86 58 48 90  6 61 11 68 62 64
Card 165: 15 54 48 34 18 25 85 23 82 43 | 79 49 95 24  9 96 58 27  2  8 77  3 90 71 86 78 31 80 28 81 26 69 57 29 17
Card 166: 23 94 29 56 27 33 51 55 79 98 | 85  7 89 74 69 15 19  2 97  5 27 88 43 80 86 62 78 76 67 63 30 20 65  1 37
Card 167: 69 37 95  7 53 80 16 82 11 27 | 22 75  5 93 47 62 76 49  2 89  9 50 21 85 34 90 38 86 45 42 18 25 33 98 99
Card 168: 95 12 30 74 21 24 80 64 17 67 | 86  8 98 19 37 48 75 56 16 31 20 40 29 83 79 73 71 27 59 43 65 61 57 78 81
Card 169: 80  8  4 83 15 47 93  2 41 25 | 69 80 93 30 75 16 25 74 61 15 56 67 47  4 85 44  2 87 19 92 70 95 41 94 81
Card 170: 91 25 44 98 29 80 63 26 72 88 |  7 57  3 38 17 44 70 75 88 92 54 14 80 26  8 40 98 55 39 32 72 29 61 63 86
Card 171: 74 37 39 87 31  6  8 38 76 32 | 67 83 34 96 68 64 69 82 54 74 63 12 25 85  9 35 50 87 59 33 60 11 20 32 84
Card 172: 45 96  3 16 97  2 98 27 51 34 | 63 82 10 95  6  5 14 24 57 69 98 23 51  3 43 91 79 35 20 36 48 44 90 84 70
Card 173: 83 69 34  6  8 44 42 33 28  5 | 86 32 56 57  3 19 91 43 80 29 78  8 93 47 73 34 85 77 48 65 83 11  2 15 26
Card 174: 22 30 18 86 72 57 26 29 38 69 | 37 21 80 26 61 68 52 33 15 74 69  9 66 81 17 40 99 78 23 65 43  1 95 79 24
Card 175: 91 47 88  2 62 39 21  4 48 49 | 14 65 12  4 51 21 42  6 79 17 57 10  7 55 98 70 47 49  5 90 18 67 58 62 54
Card 176: 76  7 72 24 57 19 70 41 67 87 | 37 59 36 95 90 28  8 58 93 97 40 57 18 80 69 66 13 54 87 27 86 62 76 84 19
Card 177: 61 23 95 38  1 85 28 51 49 53 |  6 34 38 33 16 39 64 57 10 11 80 78 60 18 12 27  5 75 28 36 25 63 88 74  8
Card 178: 22 69 42 55 54 52 58 11 89 70 | 54 75  5 88 74 97 64 59 94 82 16 76 58 20 23 90 28 47 67 38 66 51 42 83 52
Card 179: 51 45 20 68 69 24 54 52 73 89 | 88  3 64  5 72  1 39 17 53 79 97  8 83 49 50 77 70 76 90 18 59 15 29 61 10
Card 180: 68 10 39 93 62 32  1 20 90 99 | 40 31 38 23 63 27 94 85 87 36 53 67 56  5 72 13 22  6 89 25 35 64 41 57 65
Card 181: 43 76 19 35 75 71 27 13 23  5 | 56 21 87 50 30 46  2 47 85 16 44 73  3 68 38 59 92 17 65 48 22 32 42 52 83
Card 182: 77  3 61 97 96 84 27 48 52 40 |  9 54 65 43  7 20 29 86 73 67 87 82 42 14 55 33 39 71  1 81 69 31 92 74 75
Card 183: 10 52 76 84 82 14 93 34 60 73 | 81 34 75 67 23 82 68 47 73  1 52 45 25 10  6 37 84 93 21 60 79 89 76 14 18
Card 184: 39 91 94 34 71 86 36  2 65 74 | 46 37 91 79 74 59 65 66 34 30 40 73 36 16 64 57 92 71  2 78 94 89 77 86 39
Card 185: 76 22 70 57 85 51 44 43 84 14 | 80 22 29 66 75  1 97 72 21 38 76 13 27 33 69 65 74  7 11 84 53 98 77 17 82
Card 186: 59 49 71 34 66 23 52 79 32 28 | 59  2 32 93 70 96 86 71  1  3 10 28 49 51 66 79 61 24 23 19 46 52  7 34 42
Card 187:  3 31 77 49 45 15 47  6 20 25 | 25 67 88 20 49  6 46 45  9 98 24  2 59 70 77 99 22 13 31 53 91 15  3 74 47
Card 188: 84 19 66 65 53 61 38 25 83 91 | 24 64 43 66 11 72 53 94 86 39 28 97 18 70 68 52  5  7 30 55 38  9 85 34 31
Card 189: 86 22 88 18 87 28 63 92  5 16 | 33 90 71 18 88 39 28 22 26 14 68  1 52 93 56 87 49 77 92 19 86 63  5 16 72
Card 190: 67 83 94 44 66 25  9 98 21 51 | 55 97 76 81 85 68 40 94 66 41 51 45 69 44 50 37 63 83 87 39  2 96 98 95 13
Card 191: 92 89 36 65  6 72 46 23 84 19 | 94 51 99  1  3 88 77 35 90 68 72 87 44 25 69 37  2 42 59 22 58 73 61 43 33
Card 192: 52 49 74 40  2 89 54 87 21 79 | 50 58 54 24 79 27 74 72 62 67 46 89 52 38 83 40 49 29 26 77 87 12  5 21  2
Card 193: 32 59 96 29 80  7 54  3 39 83 | 83 21 80 98 13 54 87 43 29 96 62 75 53 68 88 65 59 33  3 32 67 39 10 77  7
Card 194: 40 12 79 91 84 54  1 74 56 38 | 31 78 38  9 25 46 10 99 54 11  2 97  5 84 37 77 27  1 50 91 43 72 12 96 56
Card 195: 80 34 51 81 55 65 49 71  8 44 | 81 71 83 92  8 51 80 53 30 55 44  2 43 16 24 69 18 57 31 49 65 47 91 79  3
Card 196: 12 22 77 95 49  1 48  8 84 47 | 61 64 63 42 55 13 96 70 73 41 92 10 58  6 86 30 14 23 91 66 87 72  9 27 51
Card 197: 90 30 14 35 11 20 99 79 56  2 | 75 59 40 15 80 36 97 44 17 77 20 10 50 29 33 39 55 32 85 71 43 26 35 24 82
Card 198: 92 98 86 34 64 32 74 17 26 53 | 32 75 97 22 66 89 74 17  8 31 69 52 41 53 34 77 45 46 68  2 96 86 95 93 21
Card 199: 32 31 52 80  2 47  1 13 89  9 | 28 32 87  9 96  7 20 12 73 60 46 78 24 69 55 58 64 14 38 97 16 92 52 79 82
Card 200:  1  8 31  4 53 15 45 22 73 13 | 22 10 73  9 81 74 83  2 11 53 15 45 46 21 62 43 69 50 32 67 41 48  5 25 24
Card 201: 45 37 26 53 80  1 20 35 68 33 |  6 51 77 11 41 53 56 18 25 27 13 42 48 14 79 16 81 59 99 29 86 78  3 15 17
Card 202: 86 56 57 83 11 19 52 69 36 17 | 65 72 11 95 73 49 25 75 15  5 84 35 18 71 44 99 26 52  9 60 45 22 14 19 94
Card 203: 76 23 26 70 12 48 60 11 72 64 | 90 56 99 59 64 62 15 84 71 11 85 93 98 33 46 86 53 39  5 60 81 43  3 78 14
Card 204: 23 75 70 14 95 84 61  9 66 77 | 79  8  1 64 50 41 32 93 58 15 33 10 28 72 82 16 77 65 25 43 39 49 13 23 83
Card 205:  2 99 53 15 32  6 16 69 21 14 | 96 98 24 66  6 47  4 54 45 46 42 13 75 11 80 18 34 35 93 79 65 37 40 92 91
Card 206: 74 30 29 66 68  2  3 34 79 87 | 63 45 88 78 98 27 97 32 38 75  9 11 71 93 55 69 56 20 12 82 81 41 80 23 94"""