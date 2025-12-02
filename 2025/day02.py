def load():
    with open("input.txt") as f:
        return f.read().split(',')

def part1(data):
    count = 0
    for item in data:
        first, last = map(int, item.split('-'))
        for n in range(first, last + 1):
            s = str(n)
            if len(s) % 2 == 0:
                mid = len(s) // 2
                if s[:mid] == s[mid:]:
                    count += n
    return count

def part2(data):
    count = 0
    for item in data:
        first, last = map(int, item.split('-'))
        for n in range(first, last + 1):
            s = str(n)
            length = len(s)
            for i in range(1, length // 2 + 1):
                chunk = s[:i]
                if length % len(chunk) == 0:
                    if chunk * (length // len(chunk)) == s:
                        count += n
                        break
    return count

if __name__ == "__main__":
    data = load()
    print("Part 1: ", part1(data))
    print("Part 2: ", part2(data))