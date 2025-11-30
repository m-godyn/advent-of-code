def load():
    with open("input.txt") as f:
        return f.read().strip().splitlines()


def part1(data):
    counter = 0
    dial = 50
    for rotation in data:
        direction = rotation[0]
        to_rotate = int(rotation[1:]) % 100
        if direction == 'L':
            dial -= to_rotate
        elif direction == 'R':
            dial += to_rotate
        if dial < 0:
            dial = 100 + dial
        if dial > 99:
            dial -= 100
        if dial == 0:
            counter += 1
    return counter

def part2(data):
    counter = 0
    dial = 50
    for rotation in data:
        direction = rotation[0]
        to_rotate = int(rotation[1:])
        counter += to_rotate // 100
        to_rotate = to_rotate % 100
        clicked_zero = False
        if direction == 'R':
            distance_to_zero = (100 - dial) % 100
        else: # 'L'
            distance_to_zero = dial % 100
        if distance_to_zero == 0:
            distance_to_zero = 100
        if distance_to_zero <= to_rotate:
            counter += 1
            clicked_zero = True
        if direction == 'L':
            dial = (dial - to_rotate) % 100
        else:
            dial = (dial + to_rotate) % 100
        if not clicked_zero and dial == 0:
            counter += 1
    return counter
    
if __name__ == "__main__":
    data = load()
    print("Part 1: ", part1(data))
    print("Part 2: ", part2(data))