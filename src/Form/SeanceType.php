<?php

namespace App\Form;

use App\Entity\Seance;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class SeanceType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('nomclasse',ChoiceType::class,[
                'choices'=> array(
                    '3A1'=>'3A1',
                    '3A2'=>'3A2',
                    '3A5'=>'3A5',
                    '3224'=>'3224',

                ),
            ])

            ->add('matiere',ChoiceType::class,[
                'choices'=> array(
                    'Mathématique'=>'Mathématique',
                    'Anglais'=>'Anglais',
                    'Science'=>'Science',
                    'Arabe'=>'Arabe',
                    'Francais'=>'Francais',
                    'Informatique'=>'Informatique',
                ),

            ])
            ->add('heure',ChoiceType::class,[
                'choices'=> array(
                    '08:00-10:00'=>'08:00-10:00',
                    '10:00-12:00'=>'10:00-12:00',
                    '14:00-16:00'=>'14:00-16:00',
                    '16:00-18:00'=>'16:00-18:00',
                ),
            ])
            //->add('dateseance')
            ->add('dateseance',ChoiceType::class,[
                'choices'=> array(
                    'Lundi'=>'Lundi',
                    'Mardi'=>'Mardi',
                    'Mercredi'=>'Mercredi',
                    'Jeudi'=>'Jeudi',
                    'Vendredi'=>'Vendredi',
                    'Samedi'=>'Samedi',
                ),
            ])
            ->add('idens')
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Seance::class,
        ]);
    }
}
