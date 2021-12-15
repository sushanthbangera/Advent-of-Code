from aoc2021.utils import file_utils

input_path1 = "C:\\MyDrive\\Study\\My_Github_Repo\\Advent-of-Code\\src\\aoc2021\\Day1\\input1"
input_path2 = "C:\\MyDrive\\Study\\My_Github_Repo\\Advent-of-Code\\src\\aoc2021\\Day1\\input2"


def get_increased_depth_count(input):
    result = 0
    for i in range(len(input) - 1):
        if input[i + 1] > input[i]:
            result += 1
    return result


def get_increased_window_depth_count(input):
    result = 0
    prev_sum = 0

    for i in range(len(input) - 3):
        sum = 0
        j = i
        while j < i + 3:
            sum += int(input[j])
            j += 1

        if sum > prev_sum:
            result += 1
        prev_sum = sum

    return result


if __name__ == '__main__':
    file_data = file_utils.read_file(input_path1)
    print(get_increased_depth_count(file_data))
    print(get_increased_window_depth_count(file_data))

    file_data = file_utils.read_file(input_path2)
    print(get_increased_depth_count(file_data))
    print(get_increased_window_depth_count(file_data))
