import winsound, time

freq = [587.33, 1567.98, 1244.51, 1174.66, 1244.51, 1174.66, 1046.50, 1174.66, 1174.66,
        932.33, 1046.50, 932.33, 1046.50, 1174.66, 783.99, 1046.50, 932.33, 783.99]

for i in freq:
    winsound.Beep(int(i), 200)
    time.sleep(0.2)

