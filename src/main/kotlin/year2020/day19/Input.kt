package year2020.day19

const val SAMPLE = """0: 4 1 5
1: 2 3 | 3 2
2: 4 4 | 5 5
3: 4 5 | 5 4
4: "a"
5: "b"

ababbb
bababa
abbbab
aaabbb
aaaabbb"""

const val INPUT = """80: 20 10 | 39 12
76: 20 10 | 39 107
9: 20 2 | 39 74
22: 39 82 | 20 40
65: 25 20 | 127 39
67: 92 20 | 24 39
109: 39 39 | 38 20
57: 107 39 | 130 20
23: 67 39 | 129 20
18: 20 39 | 39 39
100: 96 20 | 109 39
84: 1 39 | 99 20
3: 20 128 | 39 110
58: 44 39 | 77 20
83: 96 39 | 107 20
73: 96 20 | 18 39
48: 82 39 | 12 20
13: 39 132 | 20 32
12: 20 39 | 20 20
87: 20 128 | 39 12
75: 39 131 | 20 35
85: 108 20 | 80 39
90: 20 18 | 39 96
127: 121 39 | 81 20
79: 39 86 | 20 36
88: 38 18
15: 39 9 | 20 59
107: 20 39 | 39 20
42: 20 51 | 39 120
37: 106 39 | 85 20
74: 39 76 | 20 97
129: 87 39 | 34 20
115: 20 18 | 39 110
40: 39 20
25: 84 39 | 112 20
97: 40 39 | 96 20
96: 20 39
101: 114 20 | 89 39
82: 39 39 | 20 20
119: 20 38 | 39 39
33: 39 39
128: 39 20 | 20 38
31: 39 43 | 20 118
4: 12 38
77: 98 39 | 30 20
49: 39 60 | 20 88
72: 96 39 | 110 20
117: 20 126 | 39 69
56: 12 39 | 109 20
43: 21 39 | 103 20
52: 109 39 | 10 20
36: 20 107 | 39 40
126: 20 26 | 39 45
69: 20 53 | 39 14
111: 17 39 | 48 20
122: 39 70 | 20 96
55: 109 38
113: 66 20 | 47 39
21: 7 39 | 105 20
112: 22 20 | 54 39
92: 12 20 | 128 39
89: 128 20 | 107 39
38: 20 | 39
34: 107 39 | 10 20
104: 20 18 | 39 130
47: 82 20 | 12 39
2: 39 66 | 20 115
71: 39 6 | 20 92
5: 20 88 | 39 27
27: 109 20 | 40 39
32: 39 90 | 20 57
123: 39 10 | 20 96
94: 20 46 | 39 109
17: 39 10 | 20 33
91: 20 13 | 39 93
103: 39 75 | 20 19
11: 42 31
63: 20 107 | 39 70
6: 20 10 | 39 18
110: 38 38
132: 39 64 | 20 104
133: 107 20 | 12 39
61: 20 12 | 39 33
16: 39 33 | 20 46
50: 39 36 | 20 6
95: 20 102 | 39 52
124: 20 70 | 39 33
131: 39 63 | 20 124
64: 20 96 | 39 107
51: 39 91 | 20 117
7: 5 39 | 111 20
99: 46 20 | 107 39
1: 82 39 | 119 20
60: 20 128 | 39 82
30: 20 122 | 39 52
125: 39 96 | 20 10
81: 125 39 | 61 20
86: 39 96 | 20 96
130: 39 20 | 20 20
93: 62 20 | 71 39
28: 130 20 | 119 39
120: 41 39 | 58 20
54: 82 38
62: 72 20 | 28 39
24: 40 20 | 10 39
102: 18 20 | 82 39
26: 20 48 | 39 83
10: 39 20 | 39 39
118: 15 20 | 65 39
70: 20 39 | 39 38
108: 39 107 | 20 128
46: 20 20
39: "a"
68: 39 119 | 20 128
59: 95 20 | 49 39
41: 23 39 | 37 20
29: 20 16 | 39 78
116: 108 39 | 99 20
35: 39 55 | 20 4
53: 94 20 | 133 39
20: "b"
98: 97 20 | 56 39
8: 42
19: 20 113 | 39 116
106: 39 3 | 20 123
44: 79 20 | 50 39
45: 39 73 | 20 68
0: 8 11
66: 39 128 | 20 12
78: 39 130 | 20 40
14: 39 100 | 20 114
105: 39 29 | 20 101
114: 39 40 | 20 18
121: 20 64 | 39 92

aabaabbaabbbabababbababa
bbaaaabaabbbabababaabbabaaabaabbbbabbbab
abababbaabbbbbbabbbbbbbaaababbbababbbbbbbabbbbaabbbbaaab
baabbabababbbabbaababbbb
bbababbbabaabbaabbbababbbabaabbababaaabb
babbbababaabbbbabbababbbbbabaaaa
bbaaababaababaaaabbaabaaabaabbbbbaaabaabbababababaaaaaba
aababbaabababaabaabbaababaabbbbbbaabbaaaababbaab
babbabbabbbaaaaaaabbabba
abababbabaabbbbbabaaabaa
bbababbbaaabbabaaaababbbbaabbbababaaabbbabbabbaaaaaaababbbbaaabaaababbab
aabbaaababaaaabaababbaba
abaaabbbbbbaabaabababbbabbbabbababaaaabbbbaabaaabbababbb
bbbaabaabaabbaaaaabbbabaababbbaa
bbaaababbbbbbbbbbbababab
aababaaaaaabbbbaaabaabbaabbbbbbaaabbbbbabbaababbabaaababaaabbbab
ababbbbaaaabbabaababbbbb
aaabaababaaaaaabaababbaabbabbbbbbbababbabbbbaaabbbbaaabb
aabbababbaabbbaaabbbbbaa
baaabbbbbaaaaaabaabaabab
aabaaaaabbbbababbaabbbbbbbababbabbaaaabaaaabaaabbabaabaa
baaaabaababbbabbaaabbabb
aabababaabbaaaababababbbabaababb
bbabbbaabbabbabababbbbbbaaaaaabaaaabaabbaabaaabbabaababbabbabbaa
aabaabaaaaabaabbaaaababb
bbbbbbbabbaaababbaababaabaaaaabb
aababbbabaabbbbbabbbabbbbaabbbbabbbbbbaaaabbbbbaababbbaa
abaaabbbbbbabbabbbbaababbaabbbbbaababaabbbbbbaab
baabbbabaabababaaaabbbaaabbbbabbabbbbbbb
aabbaabbbbbbbbbbbbaaababaabbbabaabbaaaaa
bbaabbaaaabbabaaabbaaaaa
aaabababaabbabababababaaaabbabbababaaabb
bbbbbbbbabbbaabbababbbab
aabbaaabbaaaaaaaaabaabaabababbaababababa
bbbbbbababbbbbbaabbbaaab
abbabbbabbbaabbabaababababbbaaaabbbaaabb
bbbabbababaabbaabbbbaaaa
bbaabbbabbbbabaababbabbaaaabababaabbbabababbabbbabbbbbbbbbaaaabbaababbab
aabbbbaaabaaabbaababaaaa
bbabbaaabaabbbaabababbaabababbaabbbabbaa
abbbababbbbabaaababaaaabaaaababababbaabaaabaababaabbbaababbaabab
babaabbabaabbabaabaaabbabbbbbaab
abaabbabbabababbbbabbbab
abaabaaabbbbbaababaabaaababbabbbababbbba
aaaaaaaaaabbbabaabbbbbab
baababbaaaabaababbbaaaaababaabababbaababbbbbbbbbbabababb
babbbaaabbbabbbabbabaababbbbbbaaabbabaaabbabbabbabaabbbabaaabaaaaababbbb
babaabbaaaaabbbbabaaabbbbbaabaabaaaaabab
aaabbbbabaaabaaabaaababaabbabbaa
bababbbaabababbbbabbbbbbaaaaabbbbbabbbbbaabbbbabaaaaaabb
abaaaabbaabbaaabbbbbbbbbbaaaaabb
babaaabaaabaabbabababaaa
abaaaaabbbaabbaabaabbabb
babbaaabbaaabbaaaaaaababbbaaaaaaabbaaababbbbbbbbbabbaaba
baababbbaaabbbbbbbaabbab
bbbbbaaaaaaaaaaaabbbbabb
aaabbbaabaaaaaaababbbaab
bbabaabbbaabbbaabbbaaaba
ababbabbbaaaaaabaaaaaababaabbaaabbaaaaab
bbabbaaababbbbbbaabbaaaabaabaabbbbbaaaba
bbbabbbbbaabbabaaababbaaaabaaaaaabbbaaaaabbbabba
bbaaaababaabbbabaaaaabbbbbabbbaa
abaaabbbaaababbbaaababaa
bbbbabaaaababababbababab
baabaabbbababbbaaaabbbab
baaaabbbaabbababbaaabbaa
abaaabbabbbbbbbaaabbbbbb
baabbbbbaababbaabbbbbbbabaaaaaababbaabbbaaaaabaaaaaaabab
baaababaaabbaaaabbababbabbaaaabaabbbbbbabababbab
aabbaaaaaaaaabbbabbbbbabbabbaaaaabaabababaaabababbbbbbab
aaaaabbbaaaaaaaaaabbabaabbbabbabbabbabaabbbbabbbaaaaaaab
bbaaaabaabbbbaaabababbbabbaaabbbbbbaaaab
bbbbaabbbbaabbbabbababaa
abbbabbbabababaaabaababb
abaaabbbbaabbaaabbaabbab
ababbaaaabbabaabaaaabbbbabbbabaaabbabaaaabbbabaabaababbaababaabaaaaabbaaaaabbaaa
abbaaabbaabbaabbbaabaaaa
aababaabbbababbbaaaaaabb
bbbaaaaababbaabaababaabb
bbbabbbaabbbaababaaabbba
bbaabaabbbabbabababbbabb
ababaaabbbaabbbabbabaaaa
baaaabaaaabbabaabbbababbbabababbababbbbb
bbbabbabaabbbbbaaabbababaaaaababaabbaaaaaaaabaabbbabbbbaaabbbaabaaabbbbabbbbbabbbaabaabaabbbbaaa
baababbbaaaabaabbbbaababaabbbbab
bbbabbabaaabbababbabbbba
bbababbbbaabbbababbabaabbabbbbaababbbabbabbbbabaabbbabba
baabaaababbabbbabaabaaaa
aababbaaaabbaaabababbaba
abbaaaabaabbabbbaabaabbaabbbabaabbabbbabaaaabaaa
abababbbbbbabbbabababbaaabaaaababaabbaabababbaaaaabbbabbbaaabbbabbaababbbaaabaabababbbaa
abababaababbbabababbbabbaaabbaaababbbbba
bbbbababbaabbabaaaabbaaa
babbbabbbaaabababaaabbababbabaaabbbaaaba
aaabbbbabbaaababaababbbaaaaabaaa
bbabbbaaababbabbabababaaaaaaabba
baababbbabbaaabbbaaababb
bbbaabababababaaaabbaabbbaaabaaaaabaabba
aaaaaaaaaabbbbaababbabbb
abaaaabbaaabaabbbababbaaabbabbabbbabbbab
aabbaabaaabaabaaababbaab
aababaaababbabaaababbaabbaababab
abbabbababaaaaabbbabaaabaabaabab
baabbbbaabbbbbbaaaabbabb
aabababaaaaaaaaaaabbbaab
babbabaabbbaababababbbbabbbaaabbaaabaaab
bbabbbaabaababaaaabbbbab
babbbabaabbbabaababbbaaa
bbaabbaabbbbababbaaaabba
aaaaaaaaaababaaababbbaab
bbaaabbaaaababbbababbaba
bbbababbbbabbabaaabbbaab
aabababaababbbbaaabbbbbb
abbbbbbababbbabbabaaaaababbaabab
abbbbaaababbbabbabbabbbb
bbbbbaaaaababbaaaaaaabba
bbaabbaabaaabbabbabaabbaababbaba
abbaabaabbababbababbabbb
abbaabaaaababbbaabbabaaabbbbabbb
abbbbaaabaabbbbabbaaabbb
babbaababbbababbbbaabbbaabbabaabbbaaaabbbaaaaaba
bbbaabaaabaabababbaabbab
bbbabaaababababbaaabaabaaabaaaab
abbaaabbbababbababbaabba
abbbaabaabbabaaaaabababababbbbba
baaaabbbabbaaabbabbbababbaaababbbaaaabab
aabbaaaabaaabbababaaaabaaaaaabba
abaaabbabbbababbbabaabaa
aaaaaaaaaaaababaaaaabbab
aabbabbbabbabbabbabbbbab
bababaababaaaabbaaaaaaabbaababba
babaabbaabbbabaabaabbbabbaaaaabbbaababba
abaaaaabbbaabbaaaaabaaaa
aaaabababaabbbbbabaaaaababbababb
baaaabaabbababbabaababaaaabababbaaaaabaaaaaabbba
ababbbbabbbbbbbbaaaabaab
baaaabaababbabbaabaaaabbbbaabbab
baabbbaaaaabaabababbabbb
aaabababbabaaaabbbbaaababaababab
bbababbbabbabaaabbabaaba
aaaabbbbaaabbbaabbababbababbaabaabaaabaa
aabbaabbbbabbbbbaaabbabaaaabaaaa
aaababbbbbaaabbaaaabbaaa
abababbabaaaabaabbabbbaaaabbbbaaabaababaaaabbbaabaaaabba
babaaababaaaaaabaabbbbab
bbabbaaabaabbaabababaaba
baababbbaababaabbababbab
aaabbbbabaabaabbaaabbbaaaabbbbba
aaaaaababbbbbbbbbaabbbbbbaabbababaaababbbaabaaba
aabbabaabaababaabbbbaabbabaaaaaa
bbbbbbabbbbaabaaabbabbabbbbbaaaabaaabbba
babababbbaabbaabaaabbbab
baaabaaabaabbbbbaabaaabb
baaaabaaababbbbabababaaa
aabaabbaaababababbaaabaa
abbabbbaaaaabaabbabbbaaa
bbbabbbbaabaaaaababbaaab
bababbbaaaabaabaababbaba
abaaabbbbbbaabababbbbaaa
aaaabbbbbaabbaaaabaaabbabbaabbabbabaaabb
aaabbababbbabbababbabaab
bbbbaabbabaaabbaabaabbba
baabbbbbbabaaaababbbbbbb
abbabbbaabbaaabbbbbaaaaababbbabaaaababababbababbababbbaaaababbbb
babababbaabbaaabababbaab
abababbbaaaaaaaaaaabbaab
abbbaababbababbabbabbabb
baabaabbbaaaaaabaababbaaababbbaabbbbabbb
bbabbaaabaabbabaaabaabbaabaaaabbbaaababbaabbbabb
aaaaaaaabaaabbabbaabbabb
aabbaaabaaabbbbbbaababaababbbbba
bbbbbbbbabbaaaabbaabbaabbbbbbabb
babbbbaabaababbbbbbaababbabababababaabaa
baabaaabbbabbabaabbbaaab
baaaaaabaabbabbaabaabaaabbabbaaabaaaabab
babababbabbaaaabaaabaaaa
bbbbbaaabbbaababaabaabbbbabaaabbbbbbbbaa
bbbbbbabaababbaaaabbababbabaabbabbababab
abbbbaaaaabbaaaaaabbaaaaaaabbaaa
abaaaabbbaabaaababbaabbb
abaaaabababaabbababbabbaaabbbabb
aaabababbbabaaabbbaabaababbaaababbbbaaaa
bbbbbaaabbaaabbabbbaabba
bbbabababbabbaaabbbbabaabaabaaababababbbaaaabbab
aaabbbbbaaabaabaabababaaabbbbbbabbbababbbbabbaababaababbbaabaaaa
baababbabbbaaaabbabbbbababbbbabbaaabbbabbbbbabba
abbabaaabaaabbbaaaabbaababbabaaaaaaabbbbbbabbbba
babbabbaabbabaabababbbbb
ababababbbbababbabbbaabaabaababbababbbbabaababbbbbabbabaaaabbaaabbbababaaabbaabbbbaabaaa
abababbbabbbaabaabbbbbaa
aaaabbbbabaaaabaababbaab
bbbabbbbbaabbaaaaaaababaaaaababb
bbaaaababbabbbaaabbabaaabbabbabbbabbaaaa
bbbaaaaaaabaaaaaabbbbbbb
aabbabbabaababbbbbababbbbabbaabababbbbab
abbabaabbbbababaabbbbababaabababaaaabbababaabaabbbbbbaba
bababaababbbbaaaaabaaaaabbabbababababaaa
aabbbabaaaaaabbbbabbaababbbbbbbababbabbb
bbababbaabbabbabbbbaaaaabbaababaaaaaabaabbaabaaabbaaaaab
babbbbbbaabababbaaaabbab
bbabaaabbbbbaabbaababbbb
baabbbbbbbbbaaabbabbbbabaabbbbabbababaaabbbbbbbabbaabbbbabbaabaa
bbaabababbbbabbbabbbaaabaaaaaabbabaaabab
aaabaabaabbbbaaaaabbaaaaaabaaabb
abaaaababbbbbbbabbaabbbb
bbbaabaaabbbaabbbbbbaaba
babbbabaaabbbbaabbbabbababaaaaba
aabaaaaaabbbabbbaabbbaab
abaaaabbabaabbbbbaaaabba
bbabaaabaabbabbabababbbbaabaaababaabaabababbabab
abaaaababbbbbbabbbabbbab
aaababbbaaabaabbbaabbabababbbbaababbaabb
aabababaaababbaababbbbaaaabaaabb
aabbabaababaaabaabbbbbab
aaaabababbaaaabaabbabbbb
aabbabbbaabbabbbaabaaabb
abaabbaaaaababbbbbaabaaa
bbbabbbbbaaabaaabbababab
aabbaaabaabaabbaaaaaabaa
aabbabaabbababbbbbaabaab
bbbbbbbabbabaabbbbabbbbbaaaaababbbabbbab
ababbabbaaabbababbbbaaaa
bbbabbbbaabbaaaaabaaaabbbbaaaabaaabababbaaababababaabbba
babbabbaaaabbbbbaababbbabbbbaaab
aaaaaaaaabababaababaaaabbbabbaaaabbbabba
bbbabbbbaabababbababaabb
bbabbabaabababaaaabbaabaababbbab
bbbabbababbaaabbbbabbaaaabaaabaa
bbaaaaaabaabbaaaabaaabbbbabbababbabaabaa
baabbbababbbbaaabbbbabba
abbaabaabaabaabbbbaabbbb
bbaabaabbbabbaaaaaababaa
bbaabbaabbabbabaaaaabbab
aabaaabbbbbbbbabbaababbbbbaababaaaaaabaabababbba
baabbaababbbbaaaaaabaaab
aababaaaaabbababaaaabbbbbbbababbbabaabaabaabaaaa
bbbbaabbabbaaaababbaaaabaabaaaba
baabbaaabbabababbbabbbbbabaabaabababbbbaabbaabbaabbbbaaa
aabbabbabababaabaaaabaaa
aabbaabaababbabbaaaaaabb
bbabbbaaaabbabaabbaabaabbabbabab
abaabaaaaabbbabaaabbbbba
abbabbabbbbabbbbbaabaaba
bbbabbbaaaabaabbbbbabbabaabbababaababbbbbbbbbbaa
bbabaaabbaaabbbbbbabbbaabbaaaabaabbbbaaababbbbbabbbaaaababbbabbbbabbbbbabbababbb
aaabbaaaabbbabaabaaabbbaaabbaabaabbabaaababbaaabaabbbbab
aaaabaabaabbaaaabbaabaababbaabba
baabbbbababbabaabbbbbaab
baaababaabbaaabaaabbbbbbaaababbaaabaaaba
baababbbbbaaaabaabbbabaabbbbbabb
bbbbabaababbbabbbbabaaaa
bbabbabaaaaaaaaabbaabaaa
bbbbabaaaaabbbbbaabababaaaababbbabbbabbbbbaaaabb
abbbabbbbabbbbaabababbbb
aaaaaabaababbaaaaaabbaab
abababbaabbbababbaaaabab
abbabaaabbbabbbbbbaaaabb
aaaabababbbbbbabbbbabaaabbbbbabbaabaaabb
abbaaabbbaabbbaaabaabaab
aababaabbbbbababbabbaabababaabbb
bbababbbabbaabaababaaaabbbabbbaaabaaaabababbbaabaaabbaab
bbbabbabaabbaaaaaabaaaba
aababbbabaabaabbabaabaab
aaaaabbbbbababbabbabaabbaaabaaaabaababab
aabbabbbaabbabaabbabaabbbbaabababaaaaabb
abbbabbbbabaaabaabbaabbaabbbbabbbbaaaaabaabbabaabababaabbbabbbaa
bbaaabababaabbaaabbabaaaabbaabaabbaabbbb
abbbaabbbaabaabbbabbabbaabbabbabaaabbbaaabbbbaababbaabbbbbbbbaab
aabbbababaaaabaabaabaaba
baaababaabbaaaababbbabbbbbbaaaba
babababbbbbbabaabbbabaaaabbabbbb
baababbbbabbbababababbbaaababaaabaaabaabbaabaaaaababbaab
baaabbbbaababbbabaaaabba
bababbbaaaaaaababbabaaaa
abbabaababbbbaaaabbbbbbb
baabbababbbbabaaaaaaabab
aaaaaababaabbaaaaabaabbb
aabbaaababaaabbaaaabaabbbaabaaba
bbbbaabbaaaabaabbbbbaabbbabbbbbbaaabbabbbbaaaaab
abbabbbaaabbaaabbaaabbabaaaabbaa
bababbbaaabbababbbbbbbabbabbbbaa
aabbbabaababbaaababbabaabbbbaababbbbaaba
bbabbaaabaaaabbbbaababaabaabbaaaaabaabaabaaaabba
bbaaaabaababbaaaabbaabab
aabaabaabaabbbbbbbaaababaabbaaaabbaaababbbaabaaa
aabbabababbbaabaabbbbaaababaabab
bbabbababaaaabbbbbbabaab
abababbbbaababaabbbbababbabbbabbbabbbbbbabbaaaba
abaaabbabaabbbbaaabaabaabaaabaaabbbbbaaa
bbbabbabbbaaabbabbabaaaa
abbbbbbaabaaaabaabbbbabb
aaabababbbabbaaaabbbaabbaaabbabaaababbbb
bbbaaaabaaaaababbbbaabaabbbbaababbbbababaaaaabbbbbabbabababbbaababbbabaa
bbbabbbbaabbaabbaabbbbab
bbbbbbabbaabbbaabbaabaaa
bbababbabbbbaababaabbabb
babaaabaababaaabbabababa
aabbaaaaaabaabaabbbababbaaabaabbbbabababbaababba
ababbaaabbbabaaababaaabb
bbbbbbbbbbaabbaababaaaababbbaaabbbbbaaab
bbabbbaabaaaaaabaaaababababbbbaaaabbaabbbbabaaaabaabaababaabaaaa
bbaaaaabaababbabbbabbbababbbbbbbababaabbbaaaaabababbbaabaabbbbbaaaaaaaabbaabbbabababbbabaaaaaaaa
babbabaaabaaaaabababaabb
baabbbaaaaababababbbbaaaaaaaabab
abaababaaabbbbbbaaabaaaabbbbbabaabbaaaaa
baabbaabbbaaabbaaaabbabb
aaabbabaabbbabaaaaababaa
bbabbbaaabbbaabbbabaabbb
bbbabaaaabaabbbbababbaba
baabaaabbaabbaabbbababab
bbbbaabbbbbababbabbaaaabbbaabbab
bbbaaaaababbbbbbbbbbbbaa
abaaaabababbbabababababa
abbbabaabaabbaaaabaaaabbabbaaaba
bababbaabbbabababababbbb
abbaaabaabbbbaaababaaaaaaabbababbabbaaaaabbaaaba
bbaaababbbbaabaabaaaaaba
bbbababbbbbababbababaaabbabbabbaaaabaaaa
bbbbaaabaabaaabbbaabaaaabbababaaababaaaa
abbaabaaababbaaaababbaba
babbbbbbaabbbababbbaaabb
bbbabbbaabbabbbabbbababbbbaaaaab
bbabbaaabaabbbbaabaabbaababaaaaaabbbbaba
babaaabaabaabbbbababaabb
aabbbbaabbbbbaaababbbbbbbaaabaaaaaababaaaabbbbbaabaababb
bbabaabbbababbaababbaabb
abbbaabababaaabaaaabbbbaababbabbbbbabbababbbabaaabbaabababbbabbaaabbbabb
bbaabbaaaabbaaaaaabababbbbbbbbabbbbabbaa
babababbbabbabaaabbbabababbbabbbbababbbb
aaababbbbaaaaaaabbaaabaa
baabbbabbbbabbabbabbabab
bbabbbaaabbbabbbbababbaaabbaabba
abaaaabbaabbaaaaaaaabbba
baaababaaaabbabababbbbbbabababbbabbaaaba
abbaaabbaaabbbbaabaabbabaaaabaaa
baaaaaabbbabbbbbbbbaaaab
aaaababaababbbbabbababaa
bbbbabaaaabbaabaaaaaabba
bbbaaaaaaababbbaaaabbaaa
abbabbabbbbabbbbbabbbabaabbabbabbbbbaaab
aababaaabbbababbbbbbbbbbbbbbabbaababaabb
abababbabbabaabbbbaababa
aaabaabbbbbbaababbabaaaabbababbbbaaaaabaabbabbbbababaaabbabbaaaaaaabaabb
baabbaaaaaaabbbbaabaaaba
bababbbaaabbaabbaababbbb
babbabaaaaabababaaaaaaaa
baaabbabababbbbaaabaabaaababaaba
abaabababbbabbabbbbbbaba
aabbaaabaabababaabbbabbbaaabbbbbbaaabbbabaaaaabaaabaabbb
babaaaabaaabbbbaaaaabbab
aabaabaababaaaabbabaaaaa
bababaaaaabaaaababbbbaaaaaabaabababbaabbbbbaaaaaababaabababaaabaaababbabaaaabbba
aabbaaabbaabbbaaabaaabbbbaababbbabbbaabaaababbab
aaabbbaaaabbbbaaaaaabbab
bbbbaabbabaaabbbbbaababa
aaabbbbaabababbaabaababa
bbabbbbbbbababbaaabbbbbb
aababbaabbbabbabaabbababbbababbbbbbaaaabbabbbbab
aabbababaaaaaaaaabaabbba
abaabaaaabaababbbaaabbbaaabaabbbbabaaaaabbabaabbaaabaabaabbaaaababbaaabbabbaaabb
aababbaabbabbbaabaaabaaabababbaaabbaaaba
bbbabbbbbbaabbbaaabbabababababbbaaababababbbbabb
aababaabbbbaaaaaaababbaababbbbaaaabbbaaa
bbbabbbaaaabbbbaabbabbbabbaaabbabaabbbbbbaaaaaba
aaabbbbbbbababbbababbbab
abaaaababbbaabaabbbabaab
abbabaaaaaabbbaaaaabaaab
babaaaaabaabababbbbaabbabaaabbaaaaaaaaab
aabbaabbaaaaaabaababbbbababbbabbbaabbabb
aabbabbabbbbabaababaaabb
abaabbabbabaaaabbababbaaaaaabababaabbaabbaabaabbaaaaaaab
ababbbabaabaaaababaabbababaababbbbaabaabaaaaabab
babababbaaaaaaaababbbbaabbbabbbbabbbabbbabbbaaababaabbba
bbbabbbbbbabaaabbaabaabbbabbaabb
abbaabaababaaabaaababaaabbaaabbbbabbbbab
baabbabababbbabbbabaabbaabbaabaaabababbaabaababbbabbbaabbbbaabbbbabaabbb
baabbaaabaabaaaaababbababababbab
aabbabaabaaaabbbbaabbaaabbaabbbababaabaa
baaabbbbaabbabaaababaaaa
bababbbaaabbaaaaaaaaabbbbaaababb
bbbbbbbbaaaabbbbaaabbbab
aaabbbbaabbaaaabbbabaaaa
babbbbaaabababaabbaabbbb
baaabaaaaaababbbbbbabbabbbaabbab
aaaaaabaaaabaabbbaabaaaa
ababbaaaabaaabbbbbbbbbbabbbabbbbaaaaaaaaaabbbabababbbbababbbaaabbbbbbbaa
bbbabaaaaabaaaaaaaabaabababbbbaabbabbbaabaabbaaabbaaabaabbbaaabbbbaaabbbabbbabbabbbbbbaa
baaaabbbbaabbaaabbbabaaabbaaaaaaaaaababababaabaa
aaaabbbbaabbabaaaabbaabaaabaabbabbaabbab
abaaaabbababbbbabbbbabba
baabbbabbabbabaabababaabaaaaaababaabbbabaaabaaababbabbbbbbbbbaababbbabba
abbaaabbbbbbabaaababaaba
aabbaabbaabbabababbabbbaaaabababbbaabaaabbaababaaabbbbbb
baabbbababbbaabbabaabbbbaabbbabbaabaabbb
bbabaaababbaaabbabbbbaba
bbabbaaabaaaaaabaababaabbbababbabaabbbaabbbaaabbbabbababbaaaaabbabbbaaab
babbbababaabbaaaaabbbaab
aababaabbbbababbbbaabaababaabaaaabaabbab
aaaabbbbbabbbabbaabaaaaaaabaaabaabbbbabb
bbabbbbbbbbbababbaabaabbbbbbbaab
abaaaabaaababaabbbbabaab
bbabbbaabbbaaaaabbaabaaa
aaaaaaaaabbabaaabababaabbbabbbba
aababbaabbbababbbbabbaab
aaababbbaabababbbabbabbb
abaabbbbabbbabaabbaaabbb
babaaabaaabbbababbaaabaa
baabbaababaaabbbabbabbaa
ababbaaaaabbabbbbbabbbaabbaabaabaaaaabbbbaabbabbbaaabaab
baaaabbbabbabbabbabbabbb
aabbabbaaabbabaabbaaaabaaababaababaabbaaabbbbbab
baaabaaaabaabbbbbabaabbb
abaaaaababbaaaabbbabbababbabbababaabaaaababbabbbbbabbaab
ababaaabaaabaabbbabbaaab
aababbbaabbabaaaaabbabbbabaabbaababbaaab
aaaababaaaaabbbbbbbaababbbaababb
baaabababbabaaabbbbbbbabaabaabaaaabababaaaaabbabaaaabbbabaaaabba
aababaaababbbbaaaabbabbbababaaabaaabaaab
aabaaaaaababbbbaaabbaabaaabaaaba
abbaabaababbabaaabbabaabaaaaabaa
aabbaaaaabbbabababbbbbaa
aaaaaaaabbbbbbaaaaaaabbababbbbaaaaaabbabbbaaabaa
bbaaababaabababaabababab
baaaaaaaabbabbabbabaaaababaabaaabbaabaaa
aaaaabbbbabbbabbaaaaabbbbbbaaabb
baaabaaaabbbababbbbabbabbbaababbbaaaaabbbaabababbbabbabb
babbbbaaabbabaabbbbabbbbbababbaabbbabbabbbaababbaabaaabababaaaaa
baaaaaaabababaabbaabaaaa
aabbaababbabaabbaaaababaabbaabaabaabbbaabbbbbabb
abbbbaaabbaaabbaabbabaababababab
bababbbabbabaaababababaabaabbbaaabaaaabbbabbabbbabbbaaab
aabbabbbbaaaaaaaaabbbbaaaabbbababbbbbbbababaabbbbabaabbb
abbbabaaabbabaabbbabbbaaabbabaababbbbbaaabbbbabb
baabbababbaabaabbaaaaaabbabbaaaabbabaaaa
aaabaabbbbbabaaabaaababb
aabbbbaabbbababbabbbbbab
baabaabbbbaabbaababbbbbbabbbbaaababaabab
babaaaabaabbaabbabaaabab
bbabaaabbabbbabaaabaabbaababaaaaaaaaabab
aabbbabaababbabbaabbabbbabaabbbbbbbbbaab
abbbaababbbbbbbbbaabaabbaaaaaaaaaabaaabb
bbbabbabbabbbbbbabaabababababaababbabbaa
babbaaaaababbbababbbbbbb
ababbaaabbaabbbaabbaaaabbbabbbbbabbbbaabbabbabab
baabaabbabaababababbabbaaabbaabbabaaaaaaaabaaababbbbbbaa
ababbababbaabaaaaabaaaabaabbaaabbaabaaaaabababaabbaaababbbabaabababbbbbb
abaababaabaabbaabaabaabaabbababbababbbaa
aababbaabaabbbabaaabbbba
baaabababaabbaabbbaaababaabbabbabbaaaaab
baaabbabbbaabbbaaaaaaaab
abbabbbaaaabaabbbabbaaaa
baaabababaabaabbbbababab
bbabbbbbaaaaabbbbabaabbb
babbbababaabbbbbaabbbbab
ababbbbaabaaaaabaabaaaaaabaaaaaa
baaabaaaababbabbaabbbabb
aaabababaaabbbbaababbbaa
aaaabababaabaaababbbbbbaaaababaaaaaaabab
baaaaaaaaababbbabbbbbbabbbaabaaa
baabbbaabbaaabbaaabaaaab
bbbabbbbabababbabbababbabaaaaaabbbbabbabbaabbaaaaababbababbbaaaaabbbabba
bbabbababaababbbabbbbbbb
aaabababbabbbabbbabaaabaabaabbbabbbaaaba
bbabbabaaabababababbaababbbbaaab
bbbababababbaabaabaaabab
aababbaaabbbabaaabbbbaba
abbaaabbbabaaaabababbbbb
bbababbbaababaaaaaabbbab
babbabbaabbbbaaababbaabb
bbaaababbbaabaabaaabaaab
abbbabaaaaaababbaaabababaaaaaabbaabbababbabbaabaaaababbaabababababaabbba
aabbababaabbaaaabbabaaaa
aaaabbbbabbbaababbbbbbbbabbabbbabaaaaaaaabbaaaabaaaababbababbbaa
aababaaabaaabbabaabbbaaa
abaabbbbbbabaaabbbaaaaaabaabbaababaababb
aabbabbabbaaaaaabbbaababbabaabbbabbbbbab
aabbabbbaaaaabbbaabbaaaabaaabbaa
baaabbbbbaababbbabaaabab
abbabbbaabaaaaabbaabbbbaaabbababbbabbaaaababbabbbbbbbaab
baabbaabbbabaaabbbaaaabb
abbaaabbbaababaabbaaabaa
aaaaaaaaabaabbabbaabbaabaabbaaaababababa
bbaabbbaabababbbaabbabbbababaababbaababa
aaaaabbbaabbaaabbabababbbabbbabbaaabbaaaababaabb
aabbaabaaabbaaabbbababab
bbaabaabaabaabaaaabaabab
aabbaabbbbaabbaabbaabbbaaaaaaabb
aaabbabaaabbabaababaabbb
aabaabaababbbbaabbabbabb
aaabbbbaabaaaaababaaaaaa
bbbaaaaababaabbabbabbabaabbbbabb
ababbbabaaabbbaaabaaababbbbabbabbbaaaabaaabaaabbaaaabbabbaaabbbbbababbaa
aaabaababbbabaaaaabababbabbaabbb
bbaaaabababababbbbabbbba
bbbababaaababbaaaaabaaaa
baaaabbbabbabaaaaabbabbabaaaaaababbabbbaaaababaaababbaab
bbbbbbabbbbaababbbaabbab
bbbabbbbabbabbababababbabbbaaaaaabaabbabbaabbabb"""