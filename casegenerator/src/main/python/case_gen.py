import datetime
import random
import pathlib

import tensorflow as tf


def create_constant():
    text = 'Дело № '
    text = text + str(random.randrange(100))
    text = text + '-'
    text = text + str(datetime.datetime.now().month)
    text = text + '/'
    text = text + str(datetime.datetime.now().year)
    return text


one_step_model = tf.saved_model.load(str(pathlib.Path(__file__).parent.resolve()) +
                                     '/one_step_case_gen_0')

states = None
next_char = tf.constant([create_constant()])
result = [next_char]

for n in range(10000):
    next_char, states = one_step_model.generate_one_step(next_char, states=states)
    result.append(next_char)

result = tf.strings.join(result)[0].numpy().decode('utf-8')

with open(str(pathlib.Path(__file__).parent.resolve())+'/generated_case.txt', 'w', encoding='utf-8') as f:
    f.write(result)
