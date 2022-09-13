
from collections import Counter

f = Counter()


B = 100
f[0] = 1.0
for _ in range(1 * B):
    ff = Counter()
    for dots, prob in f.items():
        for i in range(1, 7):
            ff[dots + i] += prob / 6
    f = ff

g = Counter()
g[0] = 1.0
for _ in range(6 * B):
    gg = Counter()
    for dots, prob in g.items():
        for i in range(0, 2):
            gg[dots + i] += prob / 2
    g = gg




ans = 0
for x in f.keys():
    for y in g.keys():
        if x > y:
            ans += f[x] * g[y]
print(ans)



