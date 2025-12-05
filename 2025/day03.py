def load():
    with open("input.txt") as f:
        return f.read().strip().splitlines()

def part1(data):
    total_joltage = 0
    bank_length = len(data[0]) - 1
    for bank in data:
        biggest_pair = max(enumerate(bank), key=lambda x: int(x[1]))
        if biggest_pair[0] == bank_length:
            total_joltage += int(max(enumerate(bank[:-1]), key=lambda x: int(x[1]))[1] + biggest_pair[1])
        else:
            total_joltage += int(biggest_pair[1] + max(enumerate(bank[biggest_pair[0]+1:]), key=lambda x: int(x[1]))[1])
    return total_joltage

def part2(data):
    return None

if __name__ == "__main__":
    data = load()
    print("Part 1: ", part1(data))
    print("Part 2: ", part2(data))